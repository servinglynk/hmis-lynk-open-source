package com.servinglynk.hmis.warehouse.csv;

import com.googlecode.jcsv.annotations.MapToColumn;
														
									

public class EnrollmentCoC extends BaseCSV  {
	@MapToColumn(column=0)
	private String EnrollmentCOCID;
	@MapToColumn(column=1)
	private String EnrollmentID;
	@MapToColumn(column=2)
	private String HouseholdID;
	@MapToColumn(column=3)
	private String ProjectID;
	@MapToColumn(column=4)
	private String PersonalID;
	@MapToColumn(column=5)
	private String InformationDate;
	@MapToColumn(column=6)
	private String CoCCode;
	@MapToColumn(column=7)
	private String DataCollectionStage;
	@MapToColumn(column=8)
	private String DateCreated;
	@MapToColumn(column=9)
	private String DateUpdated;
	@MapToColumn(column=10)
	private String UserID;
	@MapToColumn(column=11)
	private String DateDeleted;
	@MapToColumn(column=12)
	private String ExportID;
		

	
	
	public String getEnrollmentID() {
		return EnrollmentID;
	}
	public void setEnrollmentID(String projectEntryID) {
		EnrollmentID = projectEntryID;
	}
	public String getProjectID() {
		return ProjectID;
	}
	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public String getPersonalID() {
		return PersonalID;
	}
	public void setPersonalID(String personalID) {
		PersonalID = personalID;
	}
	
	public String getEnrollmentCOCID() {
		return EnrollmentCOCID;
	}
	public void setEnrollmentCOCID(String enrollmentCOCID) {
		EnrollmentCOCID = enrollmentCOCID;
	}
	public String getInformationDate() {
		return InformationDate;
	}
	public void setInformationDate(String informationDate) {
		InformationDate = informationDate;
	}
	public String getCoCCode() {
		return CoCCode;
	}
	public void setCoCCode(String coCCode) {
		CoCCode = coCCode;
	}
	public String getDataCollectionStage() {
		return DataCollectionStage;
	}
	public void setDataCollectionStage(String dataCollectionStage) {
		DataCollectionStage = dataCollectionStage;
	}
	public String getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return DateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		DateUpdated = dateUpdated;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getDateDeleted() {
		return DateDeleted;
	}
	public void setDateDeleted(String dateDeleted) {
		DateDeleted = dateDeleted;
	}
	public String getExportID() {
		return ExportID;
	}
	public void setExportID(String exportID) {
		ExportID = exportID;
	}
	public String getHouseholdID() {
		return HouseholdID;
	}
	public void setHouseholdID(String householdID) {
		HouseholdID = householdID;
	}
	
}
