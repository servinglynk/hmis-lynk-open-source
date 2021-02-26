/**
 *
 */
package com.servinglynk.hmis.warehouse.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Project;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.enums.NoYesEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectContinuumprojectEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectHousingTypeEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectProjecttypeEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectResidentialaffiliationEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectTargetpopulationEnum;
import com.servinglynk.hmis.warehouse.enums.ProjectTrackingmethodEnum;
import com.servinglynk.hmis.warehouse.model.base.GlobalProjectEntity;
import com.servinglynk.hmis.warehouse.model.base.GlobalProjectMapEntity;
import com.servinglynk.hmis.warehouse.model.base.HmisUser;
import com.servinglynk.hmis.warehouse.model.base.ProjectGroupEntity;
import com.servinglynk.hmis.warehouse.model.v2020.Error2020;
import com.servinglynk.hmis.warehouse.model.v2020.HmisBaseModel;
import com.servinglynk.hmis.warehouse.model.v2020.Organization;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class ProjectDaoImpl extends ParentDaoImpl implements ProjectDao {
	private static final Logger logger = LoggerFactory
			.getLogger(ProjectDaoImpl.class);
	@Autowired
	private ParentDaoFactory factory;

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {

		List<Project> projects = domain.getExport().getProject();
		com.servinglynk.hmis.warehouse.model.v2020.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2020.Export) getModel(com.servinglynk.hmis.warehouse.model.v2020.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2020.Project.class, getProjectGroupCode(domain));
		if(projects !=null && projects.size() > 0)
		{
			for(Project project : projects)
			{
				com.servinglynk.hmis.warehouse.model.v2020.Project projectModel = null;
				try {
					projectModel = getModelObject(domain, project,data,modelMap);
					 if(projectModel.isIgnored()) {
							continue;
						}
					//projectModel.setAffiliations(affiliation);
					projectModel.setContinuumproject(ProjectContinuumprojectEnum.lookupEnum(project.getContinuumProject()));
					Organization organization = (Organization) getModel(Organization.class, project.getOrganizationID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					projectModel.setOrganizationid(organization);
					projectModel.setProjectname(project.getProjectName());
					projectModel.setProjectcommonname(project.getProjectCommonName());
					projectModel.setProjecttype(ProjectProjecttypeEnum.lookupEnum(project.getProjectType()));
					projectModel.setResidentialaffiliation(ProjectResidentialaffiliationEnum.lookupEnum(project.getResidentialAffiliation()));
					projectModel.setOperatingStartDate(BasicDataGenerator.getLocalDateTime(project.getOperatingStartDate()));
					projectModel.setOperatingEndDate(BasicDataGenerator.getLocalDateTime(project.getOperatingEndDate()));
					projectModel.setHmisParticipatingProject(NoYesEnum.lookupEnum(project.getHmisParticipatingProject()));
					projectModel.setHousingType(ProjectHousingTypeEnum.lookupEnum(project.getHousingType()));
					projectModel.setTargetpopulation(ProjectTargetpopulationEnum.lookupEnum(project.getTargetPopulation()));
					projectModel.setTrackingmethod(ProjectTrackingmethodEnum.lookupEnum(project.getTrackingMethod()));
					String pitCount = project.getPitCount();
					if(pitCount !=null) {
						try 
						{
							int parseInt = Integer.parseInt(pitCount);
							projectModel.setPitCount(parseInt);
						}catch(NumberFormatException e) {
							// Eat the NFE
						}
					}
					projectModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(project.getDateCreated()));
					projectModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(project.getDateUpdated()));
					projectModel.setExport(exportEntity);
					manageGolbalProjects(projectModel, domain.getUpload().getProjectGroupCode(), domain.getUserId(), "2020");
					performSaveOrUpdate(projectModel,domain);
				}catch(Exception e) {
					String errorMessage = "Exception because of the project::"+project.getProjectID() +" Exception ::"+e.getMessage();
					if(projectModel != null){
						Error2020 error = new Error2020();
						error.model_id = projectModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = projectModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = projectModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2020.Project.class.getSimpleName(), domain,exportEntity);
	}
	
	public void manageGolbalProjects(com.servinglynk.hmis.warehouse.model.v2020.Project project,String projectGroupCode,UUID userId,String schemaYear) {
		GlobalProjectEntity entity = factory.getGlobalProjectDao().getGlobalProject(project.getProjectname(),project.getSourceSystemId(), projectGroupCode);
		if(entity==null) {
			entity = new GlobalProjectEntity();
			entity.setProjectCommonName(project.getProjectcommonname());
			entity.setProjectName(project.getProjectname());
			entity.setSourceSystemId(project.getSourceSystemId());
			entity.setId(project.getId());
			entity.setDescription(project.getProjectname());
			entity.setDateCreated(LocalDateTime.now());
			entity.setDateUpdated(LocalDateTime.now());
			entity.setUser(userId);
			entity.setProjectGroupCode(projectGroupCode);
			factory.getGlobalProjectDao().create(entity);
			
			GlobalProjectMapEntity mapEntity = new GlobalProjectMapEntity();
			mapEntity.setDateCreated(LocalDateTime.now());
			mapEntity.setDateUpdated(LocalDateTime.now());
			mapEntity.setProjectGroupCode(projectGroupCode);
			mapEntity.setUser(userId);
			mapEntity.setProjectId(project.getId());
			mapEntity.setSource(schemaYear);
			mapEntity.setGlobalProject(entity);
			factory.getGlobalProjectDao().addProjectToGlobalProject(mapEntity);
		} else {
			GlobalProjectMapEntity entity2 = factory.getGlobalProjectDao().getProjectMap(entity.getId(), schemaYear);
			  if(entity2==null) {
				  GlobalProjectMapEntity mapEntity = new GlobalProjectMapEntity();
				  mapEntity.setDateCreated(LocalDateTime.now());
				  mapEntity.setDateUpdated(LocalDateTime.now());
				  mapEntity.setProjectGroupCode(projectGroupCode);
				  mapEntity.setProjectId(project.getId());
				  mapEntity.setSource(schemaYear);
				  mapEntity.setGlobalProject(entity);
				  mapEntity.setProjectId(project.getId());
				  mapEntity.setUser(userId);
				  factory.getGlobalProjectDao().addProjectToGlobalProject(mapEntity);
			  }
		}
	}

	public com.servinglynk.hmis.warehouse.model.v2020.Project getModelObject(ExportDomain domain, Project project ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2020.Project modelFromDB = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			modelFromDB = (com.servinglynk.hmis.warehouse.model.v2020.Project) getModel(com.servinglynk.hmis.warehouse.model.v2020.Project.class, project.getProjectID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(domain.isReUpload()) {
			if(modelFromDB != null) {
				return modelFromDB;
			}
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2020.Project();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true); 
			data.i++;
			return modelFromDB;
		}
		
		if(modelFromDB == null) {
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2020.Project();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true); 
			data.i++;
		}
		com.servinglynk.hmis.warehouse.model.v2020.Project model = new com.servinglynk.hmis.warehouse.model.v2020.Project();
		// org.springframework.beans.BeanUtils.copyProperties(modelFromDB, model);
		model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(project.getDateUpdated()));
		performMatch(domain, modelFromDB, model, data);
		hydrateCommonFields(modelFromDB, domain,project.getProjectID(),data);
		return modelFromDB;
	}
	

	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub

	}

	   public com.servinglynk.hmis.warehouse.model.v2020.Project createProject(com.servinglynk.hmis.warehouse.model.v2020.Project project){
		   project.setId(UUID.randomUUID());
		   insert(project);
		   return project;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2020.Project updateProject(com.servinglynk.hmis.warehouse.model.v2020.Project project){
		   update(project);
		   return project;
	   }
	   public void deleteProject(com.servinglynk.hmis.warehouse.model.v2020.Project project){
	       delete(project);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2020.Project getProjectById(UUID projectId){
		      DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Project.class);
		      criteria.add(Restrictions.eq("id", projectId));
		      List<com.servinglynk.hmis.warehouse.model.v2020.Project> projects = (List<com.servinglynk.hmis.warehouse.model.v2020.Project>) findByCriteria(criteria);
		      if(!projects.isEmpty()) return projects.get(0);
		      return null;
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2020.Project> getAllProjects(String projectGroupCode,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Project.class);
	       criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
	       return (List<com.servinglynk.hmis.warehouse.model.v2020.Project>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getProjectCount(String projectGroupCode){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Project.class);
	       criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
	       return countRows(criteria);
	   }
	   @Override
	   public List<com.servinglynk.hmis.warehouse.model.v2020.Project> getProjectsForExport(String projectGroupCode,List<String> projectIds,LocalDateTime startDate, LocalDateTime endDate){
	       List<UUID> projectUUIDs = new ArrayList<UUID>();
	       if(CollectionUtils.isNotEmpty(projectIds)) {
	    	   projectIds.forEach(projectId -> projectUUIDs.add(UUID.fromString(projectId)));
	       }
		   DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Project.class);
	       criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
	       criteria.add(Restrictions.in("id",projectUUIDs));
	     
	       Criterion dateCreatedStartDate = Restrictions.ge("dateCreated",startDate);
	       Criterion dateUpdatedStartDate = Restrictions.ge("dateUpdated",startDate);
	       Criterion dateCreatedEndDate = Restrictions.le("dateCreated",endDate);
	       Criterion dateUpdatedEndDate = Restrictions.le("dateUpdated",endDate);
	       
	       criteria.add(Restrictions.or(dateCreatedStartDate,dateUpdatedStartDate));
	       criteria.add(Restrictions.or(dateCreatedEndDate,dateUpdatedEndDate));
	       
	       return (List<com.servinglynk.hmis.warehouse.model.v2020.Project>) findByCriteria(criteria);
	   }
	   
	   
	   /***
		 * populates User Id and project group code.
		 * @param className
		 * @param projectGroupCode
		 * @return
		 */
		public void populateUserProjectGroupCode(HmisBaseModel model,String caller) {
			if(model != null && caller != null) {
				HmisUser user = factory.getHmisUserDao().findByUsername(caller);
				if(user != null) {
					model.setUserId(user.getId());
					ProjectGroupEntity projectGroupEntity = user.getProjectGroupEntity();
					if(projectGroupEntity != null) {
						model.setProjectGroupCode(projectGroupEntity.getProjectGroupCode());
					}
				}
			}
		}
		
		@Override
		public com.servinglynk.hmis.warehouse.model.v2020.Project checkProjectExists(String projectName, String sourceSystemId) {
			DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2020.Project.class);
			criteria.add(Restrictions.eq("projectname", projectName));
			criteria.add(Restrictions.eq("sourceSystemId", sourceSystemId));
			List<com.servinglynk.hmis.warehouse.model.v2020.Project> projects = (List<com.servinglynk.hmis.warehouse.model.v2020.Project>) findByCriteria(criteria);
			if(projects.isEmpty()) return null;
			return projects.get(0);
		}
}
