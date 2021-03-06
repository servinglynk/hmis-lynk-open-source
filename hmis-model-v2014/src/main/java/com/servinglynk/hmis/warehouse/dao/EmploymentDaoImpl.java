/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Employment;
import com.servinglynk.hmis.warehouse.enums.DataCollectionStageEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmployedEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmploymentTypeEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentNotEmployedReasonEnum;
import com.servinglynk.hmis.warehouse.model.v2014.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2014.Error2014;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class EmploymentDaoImpl extends ParentDaoImpl implements EmploymentDao {

	private static final Logger logger = LoggerFactory
			.getLogger(EmploymentDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		List<Employment> employmentList  = domain.getExport().getEmployment();
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Employment.class, getProjectGroupCode(domain));
		com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(com.servinglynk.hmis.warehouse.model.v2014.Employment.class.getSimpleName(),com.servinglynk.hmis.warehouse.model.v2014.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		if(employmentList!=null && !employmentList.isEmpty())
		{
			for(Employment e : employmentList) {
				processData(e, domain, data, modelMap, relatedModelMap, exportEntity);
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2014.Employment.class.getSimpleName(), domain, exportEntity);
	}
	public void processData(Employment employment,ExportDomain domain,Data data,Map<String,HmisBaseModel> modelMap,Map<String,HmisBaseModel> relatedModelMap,com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity) {
		com.servinglynk.hmis.warehouse.model.v2014.Employment model = null;
		try {
			model = getModelObject(domain, employment,data,modelMap);
			model.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateCreated()));
			model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateUpdated()));
			model.setEmployed(EmploymentEmployedEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getEmployed())));
			model.setEmploymentType(EmploymentEmploymentTypeEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getEmploymentType())));;
			model.setNotEmployedReason(EmploymentNotEmployedReasonEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getNotEmployedReason())));
			model.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
			Enrollment enrollmentModel = (Enrollment) getModel(com.servinglynk.hmis.warehouse.model.v2014.Employment.class.getSimpleName(),Enrollment.class, employment.getProjectEntryID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
			model.setEnrollmentid(enrollmentModel);
			model.setExport(exportEntity);
			model.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
			model.setDataCollectionStage(DataCollectionStageEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getDataCollectionStage())));
			performSaveOrUpdate(model);
		} catch(Exception e) {
			String errorMessage = "Exception in:"+employment.getProjectEntryID()+  ":: Exception" +e.getLocalizedMessage();
			if (model != null) {
				Error2014 error = new Error2014();
				error.model_id =  model.getId();
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
	public com.servinglynk.hmis.warehouse.model.v2014.Employment getModelObject(ExportDomain domain, Employment employment ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2014.Employment modelFromDB = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			modelFromDB = (com.servinglynk.hmis.warehouse.model.v2014.Employment) getModel(com.servinglynk.hmis.warehouse.model.v2014.Employment.class.getSimpleName(),com.servinglynk.hmis.warehouse.model.v2014.Employment.class, employment.getEmploymentID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(modelFromDB == null) {
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2014.Employment();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true);
			
		}
		com.servinglynk.hmis.warehouse.model.v2014.Employment model = new com.servinglynk.hmis.warehouse.model.v2014.Employment();
		// org.springframework.beans.BeanUtils.copyProperties(modelFromDB, model);
		model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateUpdated()));
		performMatch(domain, modelFromDB, model, data);
		hydrateCommonFields(model, domain,employment.getEmploymentID(),data);
		return model;
	}
	   public com.servinglynk.hmis.warehouse.model.v2014.Employment createEmployment(com.servinglynk.hmis.warehouse.model.v2014.Employment employment){
	       employment.setId(UUID.randomUUID()); 
	       insert(employment);
	       return employment;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2014.Employment updateEmployment(com.servinglynk.hmis.warehouse.model.v2014.Employment employment){
	       update(employment);
	       return employment;
	   }
	   public void deleteEmployment(com.servinglynk.hmis.warehouse.model.v2014.Employment employment){
	       delete(employment);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2014.Employment getEmploymentById(UUID employmentId){ 
		      DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Employment.class);
		      criteria.add(Restrictions.eq("id", employmentId));
		      List<com.servinglynk.hmis.warehouse.model.v2014.Employment> entities = (List<com.servinglynk.hmis.warehouse.model.v2014.Employment>) findByCriteria(criteria);
		      if(!entities.isEmpty()) return entities.get(0);
		      return null;
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2014.Employment> getAllEnrollmentEmployments(UUID enrollmentId,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return (List<com.servinglynk.hmis.warehouse.model.v2014.Employment>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getEnrollmentEmploymentsCount(UUID enrollmentId){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return countRows(criteria);
	   }
}
