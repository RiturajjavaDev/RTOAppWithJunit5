package com.transport.rto.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.rto.entity.VehicleOwnerAddrEntity;
import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.repositories.VhclOwnerAddrRepositories;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.VehicleOwnerAddressService;

/**
 * 
 * used to provide implementation for VehicleOwnerAddressService to perform some
 * business operation and with the help repo we send the database
 * 
 * @author Rituraj
 *
 */
@Service
public class VehicleOwnerAddressServiceImpl implements VehicleOwnerAddressService {

	/**
	 * enable logging for {@link VehicleOwnerDetailsServiceImpl} class
	 */
	private static final Logger log = LoggerFactory.getLogger(VehicleOwnerAddressServiceImpl.class);
	/**
	 * inject VhclOwnerAddrRepositories to store data in database
	 */
	@Autowired
	private VhclOwnerAddrRepositories addRepo;
	/**
	 * inject VhclOwnerDtlsRepository for performing OneToOne Mapping in database
	 */
	@Autowired
	private VhclOwnerDtlsRepository dtlsRepo;

	/**
	 * injecting service for Registration after address data stored we need to call
	 * service to inject registration data and generate registration number
	 */
	/**
	 * inject VhclOwnerDtlsRepository for performing OneToOne Mapping in database
	 */
	@Override
	public Integer saveOwnerAddr(VehicleOwnerAddress addr, Integer ownerId) {
		log.info("****** saveOwnerAddr method  from VehicleOwnerAddressServiceImpl is executions starts*****");
		Integer addressId = null;
		VehicleOwnerAddrEntity addrEntity = new VehicleOwnerAddrEntity();
		Optional<VehicleOwnerDtlsEntity> ownerDtls = dtlsRepo.findById(ownerId);
		if (ownerDtls.isPresent()) {
			log.info("****** saveOwnerAddr method if block started*****");
			VehicleOwnerDtlsEntity ownerDtlsEntity = ownerDtls.get();
			BeanUtils.copyProperties(addr, addrEntity);
			addrEntity.setDtlsEntity(ownerDtlsEntity);
			System.out.println(addrEntity);
			VehicleOwnerAddrEntity addrEntity2 = addRepo.save(addrEntity);
			addressId = addrEntity2.getAddressId();
			log.info("****** saveOwnerAddr method if block ended*****");
			return addressId;
		}
		log.info("****** saveOwnerAddr method  from VehicleOwnerAddressServiceImpl is execution ends*****");
		return addressId;

	}

	/**
	 * this mehtod is ued to fetch data based on primary key mainly when user cliks
	 * on previous link then only that metod is executed
	 * 
	 */
	@Override
	public VehicleOwnerAddress findAddrbyAddrId(Integer id) {
		log.info("******findAddrbyAddrId method  from VehicleOwnerAddressServiceImpl is execution Starts*****");
		VehicleOwnerAddress address = new VehicleOwnerAddress();
		Optional<VehicleOwnerAddrEntity> addrEntity = addRepo.findById(id);
		if (addrEntity.isPresent()) {
			log.info("******findAddrbyAddrId method   If block satrted*****");
			VehicleOwnerAddrEntity addrEntity2 = addrEntity.get();
			BeanUtils.copyProperties(addrEntity2, address);
			log.info("******findAddrbyAddrId method   If block Ended*****");
			return address;
		} 
		log.info("******findAddrbyAddrId method  from VehicleOwnerAddressServiceImpl is execution Ends*****");
		return address;
	}

	/**
	 * this operation is used to fetch VehicleOwnerAddress data based on Owner key
	 * because we use here foreign key relationship wit OwnerDtls data
	 */
	@Override
	public VehicleOwnerAddress findAddrbyOwnerId(Integer id) {
		VehicleOwnerAddress address =  new VehicleOwnerAddress();
		log.info("******findAddrbyOwnerId method  from VehicleOwnerAddressServiceImpl is execution Starts*****");
		Optional<VehicleOwnerAddrEntity> addrEntity = addRepo.findByOwnerId(id);
		if (addrEntity.isPresent()) {
			log.info("******findAddrbyOwnerId method  if Block execute*****");
			VehicleOwnerAddrEntity addrEntity2 = addrEntity.get();
			BeanUtils.copyProperties(addrEntity2, address);
			log.info("******findAddrbyOwnerId method  if Block COmpleted*****");
		return address;	
		}
		log.info("******findAddrbyOwnerId method  from VehicleOwnerAddressServiceImpl is execution Ends*****");
		return address;
	}
}
