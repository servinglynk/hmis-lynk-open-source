/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

/**
 * @author Sandeep
 *
 */
public interface OrganizationDao extends ParentDao {

/*	com.servinglynk.hmis.warehouse.model.live.Organization getOrganizationByYd(UUID organizationId);*/
	
	
	   com.servinglynk.hmis.warehouse.model.v2020.Organization createOrganization(com.servinglynk.hmis.warehouse.model.v2020.Organization organization);
	   com.servinglynk.hmis.warehouse.model.v2020.Organization updateOrganization(com.servinglynk.hmis.warehouse.model.v2020.Organization organization);
	   void deleteOrganization(com.servinglynk.hmis.warehouse.model.v2020.Organization organization);
	   com.servinglynk.hmis.warehouse.model.v2020.Organization getOrganizationById(UUID organizationId);
	   List<com.servinglynk.hmis.warehouse.model.v2020.Organization> getAllOrganizations(String projectGroupCode, Integer startIndex, Integer maxItems);
	   long getOrganizationCount(String projectGroupCode);
}
