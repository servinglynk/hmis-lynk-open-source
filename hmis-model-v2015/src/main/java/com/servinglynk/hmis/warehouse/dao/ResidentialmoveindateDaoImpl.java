/**
 *
 */
package com.servinglynk.hmis.warehouse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.hadoop.hbase.thrift2.generated.THBaseService.Iface;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.ResidentialMoveInDate;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.enums.ResidentialmoveindateInpermanenthousingEnum;
import com.servinglynk.hmis.warehouse.model.stagv2015.Enrollment;
import com.servinglynk.hmis.warehouse.model.stagv2015.Export;
import com.servinglynk.hmis.warehouse.model.stagv2015.Residentialmoveindate;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class ResidentialmoveindateDaoImpl extends ParentDaoImpl implements
		ResidentialmoveindateDao {

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain) {
		List<ResidentialMoveInDate> residentialMoveInDates = domain.getExport().getResidentialMoveInDate();
		hydrateBulkUploadActivityStaging(residentialMoveInDates, com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate.class.getSimpleName(), domain);
		if(residentialMoveInDates != null && !residentialMoveInDates.isEmpty())
		{
			for( ResidentialMoveInDate residentialMoveInDate : residentialMoveInDates)
			{
				UUID id = UUID.randomUUID();
				Residentialmoveindate residentialmoveindateModel = new Residentialmoveindate();
				residentialmoveindateModel.setId(id);
				residentialmoveindateModel.setInpermanenthousing(ResidentialmoveindateInpermanenthousingEnum.lookupEnum(BasicDataGenerator.getStringValue(residentialMoveInDate.getInPermanentHousing())));
				residentialmoveindateModel.setResidentialmoveindate(BasicDataGenerator.getLocalDateTime(residentialMoveInDate.getResidentialMoveInDate()));
				residentialmoveindateModel.setDateCreated(LocalDateTime.now());
				residentialmoveindateModel.setDateUpdated(LocalDateTime.now());
				residentialmoveindateModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(residentialMoveInDate.getDateCreated()));
				residentialmoveindateModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(residentialMoveInDate.getDateUpdated()));
				Enrollment enrollment = (Enrollment) get(Enrollment.class, domain.getEnrollmentProjectEntryIDMap().get(residentialMoveInDate.getProjectEntryID()));
				residentialmoveindateModel.setEnrollmentid(enrollment);
				com.servinglynk.hmis.warehouse.model.stagv2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.stagv2015.Export) get(com.servinglynk.hmis.warehouse.model.stagv2015.Export.class, domain.getExportId());
				residentialmoveindateModel.setExport(exportEntity);
				exportEntity.addResidentialmoveindate(residentialmoveindateModel);
				hydrateCommonFields(residentialmoveindateModel, domain);
				insertOrUpdate(residentialmoveindateModel);
			}
		}
	}

	@Override
	public void hydrateLive(Export export) {
		Set<Residentialmoveindate> residentialmoveindates = export.getResidentialmoveindates();
		hydrateBulkUploadActivity(residentialmoveindates, com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate.class.getSimpleName(), export);
		if(residentialmoveindates != null && !residentialmoveindates.isEmpty()) {
			for(Residentialmoveindate residentialmoveindate : residentialmoveindates) {
				if(residentialmoveindate != null) {
					com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate target = new com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate();
					BeanUtils.copyProperties(residentialmoveindate, target, getNonCollectionFields(target));
					com.servinglynk.hmis.warehouse.model.v2015.Enrollment enrollmentModel = (com.servinglynk.hmis.warehouse.model.v2015.Enrollment) get(com.servinglynk.hmis.warehouse.model.v2015.Enrollment.class, residentialmoveindate.getEnrollmentid().getId());
					target.setEnrollmentid(enrollmentModel);
					com.servinglynk.hmis.warehouse.model.v2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class, export.getId());
					target.setExport(exportEntity);
					exportEntity.addResidentialmoveindate(target);
					target.setDateCreated(LocalDateTime.now());
					target.setDateUpdated(LocalDateTime.now());
					insertOrUpdate(target);
				}
			}
		}
	}

	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void performSave(Iface client, Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected List performGet(Iface client, Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	   public com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate createResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate residentialmoveindate){
	       residentialmoveindate.setId(UUID.randomUUID());
	       insert(residentialmoveindate);
	       return residentialmoveindate;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate updateResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate residentialmoveindate){
	       update(residentialmoveindate);
	       return residentialmoveindate;
	   }
	   public void deleteResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate residentialmoveindate){
	       delete(residentialmoveindate);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate getResidentialmoveindateById(UUID residentialmoveindateId){
	       return (com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate) get(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate.class, residentialmoveindateId);
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate> getAllEnrollmentResidentialmoveindates(UUID enrollmentId,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return (List<com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getEnrollmentResidentialmoveindatesCount(UUID enrollmentId){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return countRows(criteria);
	   }
}
