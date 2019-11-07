package com.transport.rto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transport.rto.entity.VehicleOwnerAddrEntity;
/**
 * this repository is used to perform operations on VehicleOwnerAddrEntity data in database 
 * 
 * @author Rituraj
 */
public interface VhclOwnerAddrRepositories extends JpaRepository<VehicleOwnerAddrEntity, Integer>{
	/**
	 * This is used to get VehicleOwnerAddrEntity based to foreign key(owner primary key)
	 * with the help of one to one mapping 
	 * @param id
	 * @return Optional<VehicleOwnerAddrEntity>
	 */
	@Query("select d from VehicleOwnerAddrEntity d where  d.dtlsEntity.vhclOwnerid=:id")
	public Optional<VehicleOwnerAddrEntity> findByOwnerId(Integer id);
}
