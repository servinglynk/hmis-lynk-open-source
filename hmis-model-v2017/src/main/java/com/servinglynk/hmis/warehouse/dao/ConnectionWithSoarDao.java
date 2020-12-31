package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar;

public interface ConnectionWithSoarDao extends ParentDao {

	   com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar createConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar);
	   com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar updateConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar);
	   void deleteConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar);
	   com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar getConnectionWithSoarById(UUID connectionWithSoarId);
	   List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar> getAllConnectionWithSoar(String geoCode, Integer startIndex, Integer maxItems);
	   long getConnectionWithSoarCount(String connectionWithSoar);
	   List<ConnectionWithSoar> getAllEnrollmentConnectionwithsoars(UUID enrollmentId, Integer startIndex,
			Integer maxItems);
	   long getEnrollmentConnectionwithsoarsCount(UUID enrollmentId);
	   List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar> getAllConnectionWithSoar(UUID enrollmentId,Integer startIndex, Integer maxItems);
	   
}
