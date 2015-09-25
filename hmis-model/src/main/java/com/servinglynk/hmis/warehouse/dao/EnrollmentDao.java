package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.live.Enrollment;

public interface EnrollmentDao extends ParentDao {

	
	com.servinglynk.hmis.warehouse.model.live.Enrollment getEnrollmentById(UUID enrollmentId);
	
	
	
	
	Enrollment createEnrollment(Enrollment enrollment);
	Enrollment updateEnrollment(Enrollment enrollment);
	void deleteEnrollment(Enrollment enrollment);
	Enrollment getEnrollmentByClientIdAndEnrollmentId(UUID enrollmentId,UUID clientId);
	List<Enrollment> getEnrollmentsByClientId(UUID clientId,Integer startIndex, Integer maxItems);
	long getEnrollmentCount(UUID clientId);
}
