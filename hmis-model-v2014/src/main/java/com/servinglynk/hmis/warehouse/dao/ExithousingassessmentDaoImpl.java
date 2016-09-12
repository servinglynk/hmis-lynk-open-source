/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.model.v2014.Error2014;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.ExitHousingAssessment;
import com.servinglynk.hmis.warehouse.enums.ExithousingassessmentHousingassessmentEnum;
import com.servinglynk.hmis.warehouse.enums.ExithousingassessmentSubsidyinformationEnum;
import com.servinglynk.hmis.warehouse.model.v2014.Exit;
import com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class ExithousingassessmentDaoImpl extends ParentDaoImpl implements
		ExithousingassessmentDao {
	private static final Logger logger = LoggerFactory
			.getLogger(ExithousingassessmentDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		List<ExitHousingAssessment> exitHousingAssessments = domain.getExport().getExitHousingAssessment();
		Data data=new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment.class, getProjectGroupCode(domain));
		com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(Exithousingassessment.class.getSimpleName(),com.servinglynk.hmis.warehouse.model.v2014.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		if(exitHousingAssessments !=null && !exitHousingAssessments.isEmpty()) 
		{
				for(ExitHousingAssessment exitHousingAssessment : exitHousingAssessments)
				{
					Exithousingassessment model = null;
					try {
						model = getModelObject(domain, exitHousingAssessment,data,modelMap);
						model.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(exitHousingAssessment.getDateCreated()));
						model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(exitHousingAssessment.getDateUpdated()));
						model.setHousingassessment(ExithousingassessmentHousingassessmentEnum.lookupEnum(BasicDataGenerator.getStringValue(exitHousingAssessment.getHousingAssessment())));
						model.setSubsidyinformation(ExithousingassessmentSubsidyinformationEnum.lookupEnum(BasicDataGenerator.getStringValue(exitHousingAssessment.getSubsidyInformation())));
						Exit exit = (Exit) getModel(Exithousingassessment.class.getSimpleName(),Exit.class,exitHousingAssessment.getExitID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
						model.setExitid(exit);
						model.setExport(exportEntity);
						if(!isFullRefresh(domain)) {
							HmisBaseModel hmisBaseModel = modelMap.get(model.getSourceSystemId());
							if(hmisBaseModel !=null) {
								modelMatch(hmisBaseModel, model);
							}	
						}
						performSaveOrUpdate(model);
					} catch(Exception e) {
						String errorMessage = "Exception in:"+exitHousingAssessment.getExitHousingAssessmentID()+  ":: Exception" +e.getLocalizedMessage();
						if (model != null) {
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
		}
		hydrateBulkUploadActivityStaging(data.i,data.j, com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment.class.getSimpleName(), domain, exportEntity);
	}
	
	public com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment getModelObject(ExportDomain domain, ExitHousingAssessment exitHousingAssessment ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment ExitHousingAssessmentModel = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			ExitHousingAssessmentModel = (com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment) getModel(Exithousingassessment.class.getSimpleName(),com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment.class, exitHousingAssessment.getExitHousingAssessmentID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(ExitHousingAssessmentModel == null) {
			ExitHousingAssessmentModel = new com.servinglynk.hmis.warehouse.model.v2014.Exithousingassessment();
			ExitHousingAssessmentModel.setId(UUID.randomUUID());
			ExitHousingAssessmentModel.setRecordToBeInserted(true);
			++data.i;
		}else{
			++data.j;
		}
		hydrateCommonFields(ExitHousingAssessmentModel, domain,exitHousingAssessment.getExitHousingAssessmentID(),data.i+data.j);
		return ExitHousingAssessmentModel;
	}
}
