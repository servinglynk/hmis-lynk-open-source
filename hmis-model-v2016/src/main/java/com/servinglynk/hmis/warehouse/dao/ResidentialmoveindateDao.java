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
public interface ResidentialmoveindateDao extends ParentDao {

	   com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate createResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate residentialmoveindate);
	   com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate updateResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate residentialmoveindate);
	   void deleteResidentialmoveindate(com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate residentialmoveindate);
	   com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate getResidentialmoveindateById(UUID residentialmoveindateId);
	   List<com.servinglynk.hmis.warehouse.model.v2016.Residentialmoveindate> getAllEnrollmentResidentialmoveindates(UUID enrollmentId,Integer startIndex, Integer maxItems);
	   long getEnrollmentResidentialmoveindatesCount(UUID enrollmentId);
}
