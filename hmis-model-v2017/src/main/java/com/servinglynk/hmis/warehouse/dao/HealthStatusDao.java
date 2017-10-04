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
public interface HealthStatusDao extends ParentDao {


	   com.servinglynk.hmis.warehouse.model.v2017.HealthStatus createHealthStatus(com.servinglynk.hmis.warehouse.model.v2017.HealthStatus HealthStatus);
	   com.servinglynk.hmis.warehouse.model.v2017.HealthStatus updateHealthStatus(com.servinglynk.hmis.warehouse.model.v2017.HealthStatus HealthStatus);
	   void deleteHealthStatus(com.servinglynk.hmis.warehouse.model.v2017.HealthStatus HealthStatus);
	   com.servinglynk.hmis.warehouse.model.v2017.HealthStatus getHealthStatusById(UUID HealthStatusId);
	   List<com.servinglynk.hmis.warehouse.model.v2017.HealthStatus> getAllEnrollmentHealthStatuses(UUID enrollmentId,Integer startIndex, Integer maxItems);
	   long getEnrollmentHealthStatusesCount(UUID enrollmentId);
}
