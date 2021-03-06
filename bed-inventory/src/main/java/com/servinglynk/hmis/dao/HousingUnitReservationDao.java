package com.servinglynk.hmis.dao;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.servinglynk.hmis.entity.HousingUnitReservationEntity;

public interface HousingUnitReservationDao {

	Page<HousingUnitReservationEntity> getClientHousingUnitReservations(UUID dedupClientId, Date fromdate, Date todate,
			Pageable pageable);
	
	PageImpl<HousingUnitReservationEntity> getHousingReservations(UUID housingUnitId, Date fromdate, Date todate,
			Pageable pageable);

}
