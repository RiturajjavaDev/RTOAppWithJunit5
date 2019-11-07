package com.transport.rto.services.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.entity.VehicleRegistrationEntity;
import com.transport.rto.genrator.IdGenrator;
import com.transport.rto.mail.sender.MailSenderUtil;
import com.transport.rto.model.VehicleRegistrationDtls;
import com.transport.rto.repositories.VchlRegistrationRepository;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.VchlRegistrationDetailsService;
/**
 * Implementation of VchlRegistrationDetailsService to provide body for operations
 * 
 * @author Rituraj
 *
 */
@Service
public class VchlRegistrationDetailsServiceImpl implements VchlRegistrationDetailsService{
	
	/**
	 *enable logging {@link VchlRegistrationDetailsServiceImpl}  class
	 */
	private static final Logger log=LoggerFactory.getLogger(VchlRegistrationDetailsServiceImpl.class);
	
	/**
	 * inject VchlRegistrationRepository to interact with db and store data in db
	 */
	@Autowired
	private VchlRegistrationRepository regDtls;
	/**
	 * to get Owner data because of onetoOne mapping we need to set owner Details also 
	 * as part of registration data to make relationship 
	 * 
	 */
	@Autowired
	private VhclOwnerDtlsRepository ownerRepo;
	/**
	 * to save vehicle data in database and return VehicleRegistrationDtls to controller
	 * 
	 */
	
	/**
	 * inject mail object for sending mail
	 */
	@Autowired
	private MailSenderUtil mailSender;
	
	@Override
	public VehicleRegistrationDtls registration(Integer ownerId,String regCenter,String vtype) throws Exception {
		log.info("*********registration  method  of VchlRegDtlsServiceImpl Execution starts********");
		VehicleRegistrationDtls dtls=new VehicleRegistrationDtls();
		VehicleRegistrationEntity entity=new VehicleRegistrationEntity();
		String regNo=IdGenrator.getRegNumber();
		Optional<VehicleOwnerDtlsEntity> ownerDtls = ownerRepo.findById(ownerId);
		if(ownerDtls.isPresent()) {
			log.info("*********registration  method if block starts*****");
			VehicleOwnerDtlsEntity ownerDtlsEntity = ownerDtls.get();
			entity.setRegDate(new Date());
			entity.setVehicleRegNumber(regNo);
			entity.setRegCenter(regCenter);
			entity.setDtlsEntity(ownerDtlsEntity);

			VehicleRegistrationEntity registrationEntity = regDtls.save(entity);
			BeanUtils.copyProperties(registrationEntity, dtls);		
				
			boolean sendMail = mailSender.sendMail(ownerDtlsEntity.getEmail(), regCenter,registrationEntity.getVehicleRegNumber(),vtype);
			log.info("*********registration  method if block completed******** mailstatus::"+sendMail);
			return dtls;
		}
		return dtls;
	}

	/**
	 * To fetch all the registration related data baed to vehicle Number
	 */
	@Override
	public VehicleRegistrationDtls findbyRegNum(String regNum) {
		log.info("*********findbyRegNum  method  of VchlRegDtlsServiceImpl Execution starts********");
		VehicleRegistrationDtls dtls=new VehicleRegistrationDtls();
		Optional<VehicleRegistrationEntity> findRegDtls = regDtls.findRegDtls(regNum);
		if(findRegDtls.isPresent()) {
			log.info("*********findbyRegNum  method If block starts********");
			VehicleRegistrationEntity registrationEntity = findRegDtls.get();
			BeanUtils.copyProperties(registrationEntity, dtls);
			log.info("*********findbyRegNum  method If block Ends********");
			return dtls;
		}
		log.info("*********findbyRegNum  method  of VchlRegDtlsServiceImpl Execution Ends********");
		return dtls;
	}
}
