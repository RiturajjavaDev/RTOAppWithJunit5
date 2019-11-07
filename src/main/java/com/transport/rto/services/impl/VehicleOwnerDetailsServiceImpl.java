package com.transport.rto.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.VehicleOwnerDetailsService;
/**
 * this is used to provide implementation for  VehicleOwnerDetailsService 
 * to perform bussiness operation on VehicleOwnerDetails 
 * @author Rituraj
 *
 */
@Service
public class VehicleOwnerDetailsServiceImpl implements VehicleOwnerDetailsService {
	
	/**
	 * enable logging for {@link VehicleOwnerDetailsServiceImpl} called
	 */
	private static final Logger log=LoggerFactory.getLogger(VehicleOwnerDetailsServiceImpl.class);
	
	/**
	 * inject VhclOwnerDtlsRepository object to store data in database
	 */
	@Autowired
	private VhclOwnerDtlsRepository dtls;
	/**
	 * this method is used to store VehicleOwnerDetails data in database with help of repositories
	 */
	@Override
	public Integer saveOwnerDtls(VehicleOwnerDetails details) {
		Integer ownerId=null;
		log.info("*******saveOwnerAddr from VehicleOwnerDetailsServiceImpl execution starts *******");
		VehicleOwnerDtlsEntity dtlsEntity=new VehicleOwnerDtlsEntity();
		BeanUtils.copyProperties(details, dtlsEntity);
		VehicleOwnerDtlsEntity entity = dtls.save(dtlsEntity);
		log.info("*******saveOwnerAddr from VehicleOwnerDetailsServiceImpl executios completed *******");
		if(entity!=null) {
			ownerId=entity.getVhclOwnerid();
			return ownerId;
		}else {
			return ownerId;
		}
		 
		
	}
	/**
	 * this is used to fetch VehicleOwnerDetails based on his primary key value
	 */
	@Override
	public VehicleOwnerDetails findById(Integer id) {
		log.info("*******findById from VehicleOwnerDetailsServiceImpl execution starts *******");
		VehicleOwnerDetails details=new VehicleOwnerDetails();
		Optional<VehicleOwnerDtlsEntity> ownrDtls = dtls.findById(id);
		if(ownrDtls.isPresent()) {
			log.info("*******findById method  if block starts  *******");
			VehicleOwnerDtlsEntity dtlsEntity = ownrDtls.get();
			BeanUtils.copyProperties(dtlsEntity, details);
			log.info("*******findById method if block ended *******");
			return details;
		}
		log.info("*******findById from VehicleOwnerDetailsServiceImpl  execution ended *******");
		return details;
	}
	
}
