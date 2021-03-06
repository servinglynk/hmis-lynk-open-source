/**
 *
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Employment;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.enums.DataCollectionStageEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmployedEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmploymentTypeEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentNotEmployedReasonEnum;
import com.servinglynk.hmis.warehouse.model.v2020.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2020.Error2020;
import com.servinglynk.hmis.warehouse.model.v2020.HmisBaseModel;
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
		com.servinglynk.hmis.warehouse.model.v2020.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2020.Export) getModel(com.servinglynk.hmis.warehouse.model.v2020.Export.class,domain.getExport().getExportID(),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2020.Employment.class, getProjectGroupCode(domain));
		if(CollectionUtils.isNotEmpty(employmentList))
		{
			for(Employment employment : employmentList)
			{
				com.servinglynk.hmis.warehouse.model.v2020.Employment employmentModel = null;
				try {
					employmentModel = getModelObject(domain, employment, data, modelMap);
					if(employmentModel.isIgnored()) {
						continue;
					}
					employmentModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateCreated()));
					employmentModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateUpdated()));
					employmentModel.setEmployed(EmploymentEmployedEnum.lookupEnum((employment.getEmployed())));
					employmentModel.setEmploymentType(EmploymentEmploymentTypeEnum.lookupEnum((employment.getEmploymentType())));;
					employmentModel.setNotEmployedReason(EmploymentNotEmployedReasonEnum.lookupEnum((employment.getNotEmployedReason())));
					employmentModel.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
					Enrollment enrollmentModel = (Enrollment) getModel(Enrollment.class, employment.getEnrollmentID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					employmentModel.setEnrollmentid(enrollmentModel);
					employmentModel.setExport(exportEntity);
					employmentModel.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
					employmentModel.setDataCollectionStage(DataCollectionStageEnum.lookupEnum((employment.getDataCollectionStage())));

					performSaveOrUpdate(employmentModel,domain);
				}catch(Exception e) {
					String errorMessage = "Exception beause of the Employment::"+employment.getEmploymentID() +" Exception ::"+e.getMessage();
					if(employmentModel != null){
						Error2020 error = new Error2020();
						error.model_id = employmentModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = employmentModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = employmentModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			}
			hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2020.Employment.class.getSimpleName(), domain,exportEntity);
		}
	}
	public com.servinglynk.hmis.warehouse.model.v2020.Employment getModelObject(ExportDomain domain, Employment employment ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2020.Employment modelFromDB = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			modelFromDB = (com.servinglynk.hmis.warehouse.model.v2020.Employment) getModel(com.servinglynk.hmis.warehouse.model.v2020.Employment.class, employment.getEmploymentID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(domain.isReUpload()) {
			if(modelFromDB != null) {
				return modelFromDB;
			}
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2020.Employment();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true); 
data.i++;
			return modelFromDB;
		}
		if(modelFromDB == null) {
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2020.Employment();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true); 
data.i++;
		} else {
			com.servinglynk.hmis.warehouse.model.v2020.Employment model = new com.servinglynk.hmis.warehouse.model.v2020.Employment();
			// org.springframework.beans.BeanUtils.copyProperties(modelFromDB, model);
			model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateUpdated()));
			performMatch(domain, modelFromDB, model, data);
		}
		hydrateCommonFields(modelFromDB, domain,employment.getEmploymentID(),data);
		return modelFromDB;
	}
	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub

	}
	   public com.servinglynk.hmis.warehouse.model.v2020.Employment createEmployment(com.servinglynk.hmis.warehouse.model.v2020.Employment employment){
	       employment.setId(UUID.randomUUID());
	       insert(employment);
	       return employment;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2020.Employment updateEmployment(com.servinglynk.hmis.warehouse.model.v2020.Employment employment){
	       update(employment);
	       return employment;
	   }
	   public void deleteEmployment(com.servinglynk.hmis.warehouse.model.v2020.Employment employment){
	       delete(employment);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2020.Employment getEmploymentById(UUID employmentId){
		      DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Employment.class);
		      criteria.add(Restrictions.eq("id", employmentId));
		      List<com.servinglynk.hmis.warehouse.model.v2020.Employment> entities = (List<com.servinglynk.hmis.warehouse.model.v2020.Employment>) findByCriteria(criteria);
		      if(!entities.isEmpty()) return entities.get(0);
		      return null;
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2020.Employment> getAllEnrollmentEmployments(UUID enrollmentId,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return (List<com.servinglynk.hmis.warehouse.model.v2020.Employment>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getEnrollmentEmploymentsCount(UUID enrollmentId){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return countRows(criteria);
	   }
}
