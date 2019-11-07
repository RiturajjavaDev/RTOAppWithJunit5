package com.transport.rto.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.rto.entity.VehicleDetailsEntity;
import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.repositories.VehicleDtlsRepository;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.VehicleDetailsService;

/**
 * provide body for performing bussiness logic in VehicleDetails class and
 * interact with db
 * 
 * @author Rituraj
 *
 */
@Service
public class VehicleDetailsServiceImpl implements VehicleDetailsService {

	/**
	 * enable logging {@link VehicleOwnerDetailsServiceImpl} class
	 */
	private static final Logger log = LoggerFactory.getLogger(VehicleDetailsServiceImpl.class);
	/**
	 * To inject VehicleDtlsRepository object to store data in database
	 */
	@Autowired
	private VehicleDtlsRepository vchldtlsRepo;
	/**
	 * toinject VhclOwnerDtlsRepository data to fetch data based on id and set in
	 * dtls object to make OneToOne
	 */
	@Autowired
	private VhclOwnerDtlsRepository vchlownerRepo;

	/**
	 * it is used to store the VehicleDetails in database
	 */
	@Override
	public Integer saveVehicleDetails(VehicleDetails det, Integer ownerId) {
		Integer dtlsId = null;
		log.info("*******saveVehicleDetails from VehicleDtlsServiceImpl Execution starts *******");
		Optional<VehicleOwnerDtlsEntity> ownerData = vchlownerRepo.findById(ownerId);
		VehicleDetailsEntity detailsEntity = new VehicleDetailsEntity();
		if (ownerData.isPresent()) {
			log.info("*******saveVehicleDetails method if block started *******");
			VehicleOwnerDtlsEntity ownerDtlsEntity = ownerData.get();
			BeanUtils.copyProperties(det, detailsEntity);
			detailsEntity.setDtlsEntity(ownerDtlsEntity);
			VehicleDetailsEntity vechicleDtls = vchldtlsRepo.save(detailsEntity);
			dtlsId = vechicleDtls.getVhicleDtlsId();
			log.info("*******saveVehicleDetails method if block completed *******");
			return dtlsId;
		}
		log.info("*******saveVehicleDetails from VehicleDtlsServiceImpl Execution ends *******");
		return dtlsId;
	}

	/**
	 * to fetch VehicleDetails when user click on previous link
	 */
	@Override
	public VehicleDetails getVehicleDetails(Integer vehiclId) {
		VehicleDetails details = new VehicleDetails();
		log.info("*******getVehicleDetails from VehicleDtlsServiceImpl Execution starts *******");
		Optional<VehicleDetailsEntity> vhclDtls = vchldtlsRepo.findById(vehiclId);
		if (vhclDtls.isPresent()) {
			log.info("*******getVehicleDetails method If Block Called *******");
			VehicleDetailsEntity detailsEntity = vhclDtls.get();
			BeanUtils.copyProperties(detailsEntity, details);
			log.info("*******getVehicleDetails method If Block completed *******");
			return details;
		} 
		log.info("*******getVehicleDetails from VehicleDtlsServiceImpl Execution ends *******");
		return details;
	}

	/**
	 * fetch data based on foreign key here owner primary key is used because we do
	 * OneToOne mapping for making a relationship
	 */
	@Override
	public VehicleDetails findVehicleByOwnerId(Integer ownerId) {
		VehicleDetails details = new VehicleDetails();
		log.info("*******findVehicleByOwnerId from VehicleDtlsServiceImpl Execution Starts *******");
		Optional<VehicleDetailsEntity> vhclDtls = vchldtlsRepo.findByOwnerId(ownerId);
		if (vhclDtls.isPresent()) {
			log.info("*******findVehicleByOwnerId method If block called *******");
			VehicleDetailsEntity detailsEntity = vhclDtls.get();
			BeanUtils.copyProperties(detailsEntity, details);
			log.info("*******findVehicleByOwnerId method If block completed *******");
			return details;
		} 
		log.info("*******findVehicleByOwnerId from VehicleDtlsServiceImpl Execution ends *******");
		return details;
	}

}
