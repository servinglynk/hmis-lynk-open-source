package com.servinglynk.hmis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.entity.HousingUnitOccupantEntity;
import com.servinglynk.hmis.service.SecurityContextUtil;


@Component
public class HousingUnitOccupantDaoImpl implements HousingUnitOccupantDao {
	
	@Autowired EntityManager entityManager;

	
	public PageImpl<HousingUnitOccupantEntity> getHousingOccupants(UUID housingUnitId, Date fromdate,Date todate,Pageable pageable) {
		Session session = entityManager.unwrap(Session.class);
		DetachedCriteria criteria = DetachedCriteria.forClass(HousingUnitOccupantEntity.class);
		criteria.createAlias("housingUnit", "housingUnit");
		criteria.add(Restrictions.eq("housingUnit.id", housingUnitId));
		 Disjunction disjunction = Restrictions.disjunction();
		if(fromdate!=null && todate != null) {
			disjunction.add(Restrictions.between("occupancyStartDate", fromdate, todate));
			disjunction.add(Restrictions.between("occupancyEndDate", fromdate, todate));
			criteria.add(disjunction);
		} else if(fromdate!=null) {
			criteria.add(Restrictions.lt("occupancyStartDate", fromdate));
			criteria.add(Restrictions.gt("occupancyEndDate",  fromdate));
		} else if(todate!=null) {
			criteria.add(Restrictions.lt("occupancyStartDate", todate));
			criteria.add(Restrictions.gt("occupancyEndDate", todate));
		}
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("projectGroupCode", SecurityContextUtil.getUserProjectGroup()));
		
		  DetachedCriteria countCriteria = criteria;
		    List<HousingUnitOccupantEntity> entities = new ArrayList<HousingUnitOccupantEntity>();
		    
		    if (pageable!=null) {
			  entities = criteria.getExecutableCriteria(session).
				   setMaxResults(pageable.getPageSize()).setFirstResult(pageable.getPageSize()*pageable.getPageNumber()).list();
		    }
		  
		    countCriteria.setProjection(Projections.rowCount());
		   
			Long count = (Long)countCriteria.getExecutableCriteria(session).uniqueResult();
			
			return new PageImpl<HousingUnitOccupantEntity>(entities,pageable,count);
		
	}
	

	@Override
	public HousingUnitOccupantEntity getClinetHousingUnitOccupants(UUID clientId, UUID bedUnitId) {
		Session session = entityManager.unwrap(Session.class);
		DetachedCriteria criteria = DetachedCriteria.forClass(HousingUnitOccupantEntity.class);
		criteria.createAlias("bedUnit", "bedUnit");
		criteria.add(Restrictions.eq("bedUnit.id", bedUnitId));
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.isNull("checkOutDate"));
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("projectGroupCode", SecurityContextUtil.getUserProjectGroup()));
		List<HousingUnitOccupantEntity> occupantEntities = criteria.getExecutableCriteria(session).list();
		if(occupantEntities.isEmpty()) return null;
		return occupantEntities.get(0);
	}

	
	
	@Override
	public Page<HousingUnitOccupantEntity> getClientHousingUnitOccupants(UUID dedupClientId, Date fromdate, Date todate,
			Pageable pageable) {
		Session session = entityManager.unwrap(Session.class);
		DetachedCriteria criteria = DetachedCriteria.forClass(HousingUnitOccupantEntity.class);
		criteria.add(Restrictions.eq("dedupClientId", dedupClientId));
		 Disjunction disjunction = Restrictions.disjunction();
		if(fromdate!=null && todate != null) {
			disjunction.add(Restrictions.between("occupancyStartDate", fromdate, todate));
			disjunction.add(Restrictions.between("occupancyEndDate", fromdate, todate));
			criteria.add(disjunction);
		} else if(fromdate!=null) {
			criteria.add(Restrictions.lt("occupancyStartDate", fromdate));
			criteria.add(Restrictions.gt("occupancyEndDate",  fromdate));
		} else if(todate!=null) {
			criteria.add(Restrictions.lt("occupancyStartDate", todate));
			criteria.add(Restrictions.gt("occupancyEndDate", todate));
		}
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("projectGroupCode", SecurityContextUtil.getUserProjectGroup()));
		
		  DetachedCriteria countCriteria = criteria;
		    List<HousingUnitOccupantEntity> entities = criteria.getExecutableCriteria(session).
				   setMaxResults(pageable.getPageSize()).setFirstResult(pageable.getPageSize()*pageable.getPageNumber()).list();
		  
		    countCriteria.setProjection(Projections.rowCount());
		   
			Long count = (Long)countCriteria.getExecutableCriteria(session).uniqueResult();
			
			return new PageImpl<HousingUnitOccupantEntity>(entities,pageable,count);
		
	}

}
