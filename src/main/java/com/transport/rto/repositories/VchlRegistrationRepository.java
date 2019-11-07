package com.transport.rto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transport.rto.entity.VehicleRegistrationEntity;
/**
 * 
 * This repository is used to perform operation on vehicle registration related data in database 
 * 
 * @author Rituraj
 *
 */
public interface VchlRegistrationRepository  extends JpaRepository<VehicleRegistrationEntity, Integer>{

	/**
	 * this  custom query method for fetching registration details based on registration number 
	 * @param regNum
	 * @return Optional<VehicleRegistrationEntity> 
	 */
	@Query("select v from VehicleRegistrationEntity v where v.vehicleRegNumber=:regNum" )
	public Optional<VehicleRegistrationEntity> findRegDtls(String regNum);
	
}
