package com.servinglynk.hmis.model;

import java.util.Date;
import java.util.UUID;

public class BedOccupant {
	private UUID id;
	private UUID clientId;
	private UUID dedupClientId;
	private UUID enrollmentId;
	private Date occupancyStartDate;
	private Date occupancyEndDate;
	private Boolean isActive;
	private BedUnit bedUnit;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public UUID getDedupClientId() {
		return dedupClientId;
	}
	public void setDedupClientId(UUID dedupClientId) {
		this.dedupClientId = dedupClientId;
	}
	public Date getOccupancyStartDate() {
		return occupancyStartDate;
	}
	public void setOccupancyStartDate(Date occupancyStartDate) {
		this.occupancyStartDate = occupancyStartDate;
	}
	public Date getOccupancyEndDate() {
		return occupancyEndDate;
	}
	public void setOccupancyEndDate(Date occupancyEndDate) {
		this.occupancyEndDate = occupancyEndDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public BedUnit getBedUnit() {
		return bedUnit;
	}
	public void setBedUnit(BedUnit bedUnit) {
		this.bedUnit = bedUnit;
	}
	public UUID getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(UUID enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
}