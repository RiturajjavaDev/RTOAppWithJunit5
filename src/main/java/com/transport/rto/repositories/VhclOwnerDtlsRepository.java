package com.transport.rto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
/**
 * this repository is used to perform operiton on VehicleOwnerDtlsEntity data  in db side
 * 
 * @author Rituraj
 */
public interface VhclOwnerDtlsRepository extends JpaRepository<VehicleOwnerDtlsEntity, Integer>{

}
