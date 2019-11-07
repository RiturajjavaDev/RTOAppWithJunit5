package com.transport.rto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.impl.VehicleOwnerDetailsServiceImpl;

@SpringBootTest
public class VehicleOwnerDetailsServiceImplTest {

	@InjectMocks
	private VehicleOwnerDetailsServiceImpl ownerService;
	
	@Mock
	private VhclOwnerDtlsRepository ownerRepo;
	
	private static VehicleOwnerDetails details;
	
	@BeforeAll
	public static void before() {
		details=new VehicleOwnerDetails();
		details.setFname("Rituraj");
		details.setLname("Rawat");
		details.setGender("Male");
		details.setEmail("Rrituraj047@gmail.com");
		details.setDob(new Date());
		details.setPhno(9845789878l);
		details.setZzn("123-33-4555");
	}
	
	
	@Test
	public void saveOwnerAddrTestPositve() {
			VehicleOwnerDtlsEntity dtlsEntity=new VehicleOwnerDtlsEntity();
			BeanUtils.copyProperties(details,dtlsEntity);
			when(ownerRepo.save(dtlsEntity)).thenReturn(new VehicleOwnerDtlsEntity(){{
				setVhclOwnerid(101);
			}});
				Integer ownerId = ownerService.saveOwnerDtls(details);
			assertNotNull(ownerId);
	}
	
	@Test
	public void saveOwnerAddrTestNegative() {
		VehicleOwnerDtlsEntity dtlsEntity=new VehicleOwnerDtlsEntity();
		BeanUtils.copyProperties(details,dtlsEntity);
		when(ownerRepo.save(dtlsEntity)).thenReturn(null);
			Integer ownerId = ownerService.saveOwnerDtls(details);
			assertNull(ownerId);		
	} 
	
	@Test
	public void findByIdTestNegative() {
		Optional<VehicleOwnerDtlsEntity> opt=Optional.empty();
		when(ownerRepo.findById(101)).thenReturn(opt);
		VehicleOwnerDetails dtls = ownerService.findById(101);
		assertEquals(new VehicleOwnerDetails(), dtls);
		
	}
	
	@Test
	public void findByIdTestPositive() {
		VehicleOwnerDtlsEntity dtlsEntity=new VehicleOwnerDtlsEntity();
		BeanUtils.copyProperties(details,dtlsEntity);
		dtlsEntity.setVhclOwnerid(101);
		Optional<VehicleOwnerDtlsEntity> opt=Optional.of(dtlsEntity);
		when(ownerRepo.findById(101)).thenReturn(opt);
			VehicleOwnerDetails dtls = ownerService.findById(101);
			assertNotNull(dtls);
			if(dtls.getVhclOwnerid()!=101) {
				fail();
			}
			
	}
	
	
	
}
