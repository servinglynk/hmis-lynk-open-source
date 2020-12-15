package com.servinglynk.hmis.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonRootName("bedUnitReservation")
@JsonInclude(value = Include.NON_NULL)
public class BedUnitReservation {

	private UUID id;
	private UUID reservedCleintId;
	private UUID reservedHouseholdId;
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date reservationStateDate; 
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date reservationEndDateDate;
	
	private BedUnit bedUnit;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getReservedCleintId() {
		return reservedCleintId;
	}
	public void setReservedCleintId(UUID reservedCleintId) {
		this.reservedCleintId = reservedCleintId;
	}
	public UUID getReservedHouseholdId() {
		return reservedHouseholdId;
	}
	public void setReservedHouseholdId(UUID reservedHouseholdId) {
		this.reservedHouseholdId = reservedHouseholdId;
	}
	public Date getReservationStateDate() {
		return reservationStateDate;
	}
	public void setReservationStateDate(Date reservationStateDate) {
		this.reservationStateDate = reservationStateDate;
	}
	public Date getReservationEndDateDate() {
		return reservationEndDateDate;
	}
	public void setReservationEndDateDate(Date reservationEndDateDate) {
		this.reservationEndDateDate = reservationEndDateDate;
	}
	public BedUnit getBedUnit() {
		if(bedUnit == null) bedUnit = new BedUnit();
		return bedUnit;
	}
	public void setBedUnit(BedUnit bedUnit) {
		this.bedUnit = bedUnit;
	}
	public void setBedUnitId(UUID bedunitid) {
		if(this.bedUnit == null) this.bedUnit = new BedUnit();
		this.bedUnit.setId(bedunitid);		
	}
}