/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import com.servinglynk.hmis.warehouse.base.util.DedupHelper;
import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.common.JsonUtil;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Client;
import com.servinglynk.hmis.warehouse.enums.ClientDobDataQualityEnum;
import com.servinglynk.hmis.warehouse.enums.ClientEthnicityEnum;
import com.servinglynk.hmis.warehouse.enums.ClientGenderEnum;
import com.servinglynk.hmis.warehouse.enums.ClientNameDataQualityEnum;
import com.servinglynk.hmis.warehouse.enums.ClientRaceEnum;
import com.servinglynk.hmis.warehouse.enums.ClientSsnDataQualityEnum;
import com.servinglynk.hmis.warehouse.enums.ClientVeteranStatusEnum;
import com.servinglynk.hmis.warehouse.model.base.ProjectGroupEntity;
import com.servinglynk.hmis.warehouse.model.v2014.Error2014;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class ClientDaoImpl extends ParentDaoImpl<com.servinglynk.hmis.warehouse.model.v2014.Client> implements ClientDao {
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ClientDaoImpl.class);
	@Autowired
	DedupHelper dedupHelper;
	
	@Autowired
	ParentDaoFactory daoFactory;
	
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
	
		Export export = domain.getExport();
		//Lets make a microservice all to the dedup micro service
		String projectGroupCode = domain.getUpload().getProjectGroupCode();
		ProjectGroupEntity projectGroupEntity = daoFactory.getProjectGroupDao().getProjectGroupByGroupCode(projectGroupCode);
		List<Client> clients = export.getClient();
		com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(com.servinglynk.hmis.warehouse.model.v2014.Client.class.getSimpleName(),com.servinglynk.hmis.warehouse.model.v2014.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Client.class, getProjectGroupCode(domain));
		if (CollectionUtils.isNotEmpty(clients)) {
			for(Client client : clients) {
				processData(client, domain, data, modelMap, null, projectGroupEntity.isDetermineDedupBySsid(),exportEntity);
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2014.Client.class.getSimpleName(), domain, exportEntity);
	}
	public void processData(Client client,ExportDomain domain,Data data,Map<String,HmisBaseModel> modelMap,String dedupSessionKey,boolean determineDedupBySsid,com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity) {
			com.servinglynk.hmis.warehouse.model.v2014.Client model = null;
			String projectGroupCode = domain.getUpload().getProjectGroupCode();
			try {
				model = (com.servinglynk.hmis.warehouse.model.v2014.Client) modelMap.get(client.getPersonalID());
				if(model == null) {
					model = new com.servinglynk.hmis.warehouse.model.v2014.Client();
					model.setRecordToBeInserted(true);
					populateClient(client, model);
				}
			model
					.setDobDataQuality(ClientDobDataQualityEnum
							.lookupEnum(BasicDataGenerator
									.getStringValue(client
											.getDOBDataQuality())));
			model.setEthnicity(ClientEthnicityEnum
					.lookupEnum(String.valueOf(client.getEthnicity())));
			model.setGender(ClientGenderEnum.lookupEnum(String
					.valueOf(client.getGender())));
			model
					.setNameDataQuality(ClientNameDataQualityEnum
							.lookupEnum(BasicDataGenerator
									.getStringValue(client
											.getNameDataQuality())));
			model.setNameSuffix(client.getNameSuffix());
			model.setOtherGender(client.getOtherGender());
			List<String> race = client.getRace();
			if(CollectionUtils.isNotEmpty(race)) {
				model.setRace(ClientRaceEnum
						.lookupEnum(race.get(0)));
				if(race.size() > 1) {
					model.setRace1(ClientRaceEnum
							.lookupEnum(race.get(1)));
				}
			}
			model
					.setSsnDataQuality(ClientSsnDataQualityEnum
							.lookupEnum(BasicDataGenerator
									.getStringValue(client
											.getSSNDataQuality())));
			model
					.setVeteranStatus(ClientVeteranStatusEnum
							.lookupEnum(BasicDataGenerator
									.getStringValue(client
											.getVeteranStatus())));
			model.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(client.getDateCreated()));
			model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(client.getDateUpdated()));
			model.setExport(exportEntity);
			hydrateCommonFields(model, domain, client.getPersonalID(), data);
			if(!model.isIgnored()) {
				if(!model.isRecordToBoInserted()) {
					++data.j;
				}
				if(model.isRecordToBoInserted()) {
					++data.i;
				}
			}
			com.servinglynk.hmis.warehouse.model.base.Client  target = new com.servinglynk.hmis.warehouse.model.base.Client();
			BeanUtils.copyProperties(model, target, new String[] {"enrollments","veteranInfoes"});
			UUID dedupId = daoFactory.getHmisClientDao().determindDedupId(target,projectGroupCode);
			model.setDedupClientId(dedupId);
			
			performSaveOrUpdate(model);	
			UUID userId = model.getUserId();
			if(model.isRecordToBoInserted()) {
				daoFactory.getClientTrackerDao().createTracker(model.getId(), model.getProjectGroupCode(), model.isDeleted(), "INSERT","BULK_UPLOAD",userId != null ? userId.toString() : null);
			}
			if(!model.isRecordToBoInserted()){
				daoFactory.getClientTrackerDao().createTracker(model.getId(), model.getProjectGroupCode(), model.isDeleted(), "UPDATE","BULK_UPLOAD",userId != null ? userId.toString() : null);
			}
			// Inserting client in base schema	
			if(!model.isIgnored()) {
				target.setDateUpdated(LocalDateTime.now());
				target.setSchemaYear("2014");
				target.setDedupClientId(model.getDedupClientId());
				insertOrUpdate(target);	
				createOrUpdatebaseCleint(target);
			}
			// Inserting client in base schema		
		
			} catch(Exception ex ){
				String errorMessage = "Exception because of the client::"+client.getPersonalID() +" Exception ::"+ex.getMessage();
				if(model != null){
					Error2014 error = new Error2014();
					error.model_id = model.getId();
					error.bulk_upload_ui = domain.getUpload().getId();
					error.project_group_code = domain.getUpload().getProjectGroupCode();
					error.source_system_id = model.getSourceSystemId();
					error.type = ErrorType.ERROR;
					error.error_description = errorMessage;
					error.date_created = model.getDateCreated();
					performSave(error);
				}
				logger.error(errorMessage);
			}
	}
	
	
    private com.servinglynk.hmis.warehouse.model.v2014.Client getClientFromDedup(com.servinglynk.hmis.warehouse.model.v2014.Client model,Client client, String projectGroupCode) {
    	com.servinglynk.hmis.warehouse.model.base.Client  target = new com.servinglynk.hmis.warehouse.model.base.Client();
		BeanUtils.copyProperties(model, target, new String[] {"enrollments","veteranInfoes"});
		UUID dedupId = daoFactory.getHmisClientDao().determindDedupId(target,projectGroupCode);
		com.servinglynk.hmis.warehouse.model.v2014.Client clientByDedupCliendId = getClientByDedupCliendId(dedupId, projectGroupCode);
		if(clientByDedupCliendId != null) {
			clientByDedupCliendId.setRecordToBeInserted(false);
		}else {
			 clientByDedupCliendId = new com.servinglynk.hmis.warehouse.model.v2014.Client();
			 clientByDedupCliendId.setRecordToBeInserted(true);
			 clientByDedupCliendId.setDedupClientId(dedupId);
			 clientByDedupCliendId.setId(UUID.randomUUID());
		}
		populateClient(client, clientByDedupCliendId);
		return clientByDedupCliendId;
	}



	public void populateClient(Client client,com.servinglynk.hmis.warehouse.model.v2014.Client model) {
		if (client.getLastName() != null) {
			model.setLastName(client.getLastName());
		}
		if (client.getMiddleName() != null) {
			model.setMiddleName(client.getMiddleName());
		}
		if (client.getFirstName() != null) {
			model.setFirstName(client.getFirstName());
		}
		model.setDob(BasicDataGenerator.getLocalDateTime(client
				.getDOB()));
		if (client.getSSN() != null) {
			model.setSsn(client.getSSN());
		}
    }

	@Override
	public com.servinglynk.hmis.warehouse.model.v2014.Client createClient(
			com.servinglynk.hmis.warehouse.model.v2014.Client client,com.servinglynk.hmis.warehouse.model.base.Client baseClient) {
			client.setId(UUID.randomUUID());
			baseClient.setSchemaYear("2014");
			client.setDateUpdated(LocalDateTime.now());
			baseClient.setDateUpdated(LocalDateTime.now());
			insert(client);
			baseClient.setId(client.getId());
			insert(baseClient);	
			createOrUpdatebaseCleint(baseClient);
		return client;
	}

	
	@SuppressWarnings("unchecked")
	public Client getClientByssid(final String ssid,final String projectGroupCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Client.class);
		criteria.add(Restrictions.eq("source_system_id", ssid.trim()));
		criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
		List<Client> clients = (List<Client>) findByCriteria(criteria);
		if(clients !=null && clients.size()>0) return clients.get(0);
		return null;
	}
	@Override
	public com.servinglynk.hmis.warehouse.model.v2014.Client updateClient(
			com.servinglynk.hmis.warehouse.model.v2014.Client client,com.servinglynk.hmis.warehouse.model.base.Client baseClient) {
		baseClient.setSchemaYear("2014");
			update(client);
			update(baseClient);
			daoFactory.getClientTrackerDao().createTracker(client.getId(), client.getProjectGroupCode(), client.isDeleted(), "UPDATE", null, null);
			createOrUpdatebaseCleint(baseClient);
		return client;
	}


	@Override
	public void deleteClient(
			com.servinglynk.hmis.warehouse.model.v2014.Client client) {
			delete(client);
			daoFactory.getClientTrackerDao().createTracker(client.getId(), client.getProjectGroupCode(), true, "DELETE",null,null);
			deleteCacheClient(client);
	}


	@SuppressWarnings("unchecked")
	@Override
	public com.servinglynk.hmis.warehouse.model.v2014.Client getClientById(UUID clientId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);
		criteria.add(Restrictions.eq("id", clientId));
		List<com.servinglynk.hmis.warehouse.model.v2014.Client> clients = (List<com.servinglynk.hmis.warehouse.model.v2014.Client>) findByCriteria(criteria);
		if(clients.size()>0) return clients.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public com.servinglynk.hmis.warehouse.model.v2014.Client getClientByDedupCliendId(UUID id,String projectGroupCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);
		criteria.add(Restrictions.eq("dedupClientId", id));
		criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
		List<com.servinglynk.hmis.warehouse.model.v2014.Client> clients = (List<com.servinglynk.hmis.warehouse.model.v2014.Client>) findByCriteria(criteria);
		if(clients !=null && clients.size()>0) return clients.get(0);
		return null;
	}
	public com.servinglynk.hmis.warehouse.model.v2014.Client getClientByDedupCliendIdFromStaging(UUID id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);
		criteria.add(Restrictions.eq("dedupClientId", id));
		List<com.servinglynk.hmis.warehouse.model.v2014.Client> clients = (List<com.servinglynk.hmis.warehouse.model.v2014.Client>) findByCriteria(criteria);
		if(clients !=null && clients.size()>0) return clients.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.servinglynk.hmis.warehouse.model.v2014.Client> getAllClients(String projectGroupCode, Integer startIndex, Integer maxItems) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);
		System.out.println(projectGroupCode);
		criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
		List<com.servinglynk.hmis.warehouse.model.v2014.Client> clients = (List<com.servinglynk.hmis.warehouse.model.v2014.Client>) findByCriteria(criteria,startIndex,maxItems);
		return clients;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<com.servinglynk.hmis.warehouse.model.v2014.Client> getAllNullDedupIdClients() {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);
		List<String> allActiveProjectGroupCodes = daoFactory.getProjectGroupDao().getAllActiveProjectGroupCodes();
		criteria.add(Restrictions.isNull("dedupClientId"));
		Criterion firstNameCriterion = Restrictions.isNotNull("firstName");
		Criterion lastNameCriterion = Restrictions.isNotNull("lastName");
		criteria.add(Restrictions.and(firstNameCriterion,lastNameCriterion));
		criteria.add(Restrictions.in("projectGroupCode", allActiveProjectGroupCodes));
		criteria.add(Restrictions.isNotNull("version"));
		criteria.add(Restrictions.le("version",3));
		List<com.servinglynk.hmis.warehouse.model.v2014.Client> clients = (List<com.servinglynk.hmis.warehouse.model.v2014.Client>) findByCriteria(criteria);
		return clients;
	}
		
	@Override
	public void updateDedupClient(
			com.servinglynk.hmis.warehouse.model.v2014.Client client,String dedupSessionKey) {
	    com.servinglynk.hmis.warehouse.model.base.Client basClient = daoFactory.getBaseClientDao().getClient(client.getId());
	    if(basClient == null) {
	    	basClient = new  com.servinglynk.hmis.warehouse.model.base.Client();
	    	BeanUtils.copyProperties(client, basClient, new String[] {"enrollments","veteranInfoes"});
	    	basClient.setSchemaYear("2014");
	     }
	     String  dedupedId = dedupHelper.getDedupedClient(basClient,dedupSessionKey);
	     logger.info("Calling Dedup Service for "+client.getFirstName());
		 client.setDateUpdated(LocalDateTime.now());
		 client.setDedupClientId(UUID.fromString(dedupedId));
		 getCurrentSession().update(client);
		 basClient.setDedupClientId(UUID.fromString(dedupedId));
		 basClient.setDateUpdated(LocalDateTime.now());
		 insert(basClient);
		 getCurrentSession().flush();
		 getCurrentSession().clear();
	}
	
	public long getClientsCount(String projectGroupCode){
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Client.class);	
		criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
		return countRows(criteria);
	}
	
	@Autowired JmsTemplate jmsMessagingTemplate;
	
	public void createOrUpdatebaseCleint(com.servinglynk.hmis.warehouse.model.base.Client baseCleint) {

		ActiveMQQueue queue = new ActiveMQQueue("cache.base.cleint");
		try {
			jmsMessagingTemplate.convertAndSend(queue,JsonUtil.coneveterObejctToString(baseCleint));
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCacheClient(com.servinglynk.hmis.warehouse.model.v2014.Client client) {

		ActiveMQQueue queue = new ActiveMQQueue("delete.cached.base.cleint");
		try {
			jmsMessagingTemplate.convertAndSend(queue, client.getId()+"");
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
