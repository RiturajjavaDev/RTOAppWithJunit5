package com.transport.rto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.rto.entity.VehicleDetailsEntity;
import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.repositories.VehicleDtlsRepository;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.impl.VehicleDetailsServiceImpl;

@SpringBootTest
public class VehicleDetailsServiceImplTest {

	
	@InjectMocks
	private  VehicleDetailsServiceImpl vehicleDetailsService;
	@Mock
	private VehicleDtlsRepository vchldtlsRepo;
	@Mock
	private VhclOwnerDtlsRepository ownerDtlsRepo;;
	
	private static  VehicleDetails vehicleDetails;
	
	@BeforeAll
	public static void beforeSetUp() {
		vehicleDetails=new VehicleDetails();
		vehicleDetails.setCompanyName("Benz");
		vehicleDetails.setMfdYear(2014);
		vehicleDetails.setVehicleType("4-wheeler");
		vehicleDetails.setColor("Blue");
		vehicleDetails.setDtlsEntity(new VehicleOwnerDtlsEntity());
		
	}
	@Test
	public void getVehicleDetailsTestPositve() {
		VehicleDetailsEntity detailsEntity=new  VehicleDetailsEntity();
		BeanUtils.copyProperties(vehicleDetails, detailsEntity);
		detailsEntity.setVhicleDtlsId(101);
		Optional<VehicleDetailsEntity> optional=Optional.of(detailsEntity);
		when(vchldtlsRepo.findById(101)).thenReturn(optional);
		VehicleDetails vehicleDetails2 = vehicleDetailsService.getVehicleDetails(101);
		assertNotNull(vehicleDetails2);
		if(vehicleDetails2.getVhicleDtlsId()==null) {
			fail();
		}
	}
	@Test
	public void getVehicleDetailsTestNegative() {
		Optional<VehicleDetailsEntity> optional=Optional.empty();
		when(vchldtlsRepo.findById(201)).thenReturn(optional);
		VehicleDetails vehicleDetails2 = vehicleDetailsService.getVehicleDetails(201);
		assertEquals(new VehicleDetails(), vehicleDetails2);
	}
	
	@Test
	public void findVehicleByOwnerIdTestPositive() {
		VehicleDetailsEntity detailsEntity=new  VehicleDetailsEntity();
		BeanUtils.copyProperties(vehicleDetails, detailsEntity);
		detailsEntity.setVhicleDtlsId(101);
		Optional<VehicleDetailsEntity> optional=Optional.of(detailsEntity);
		when(vchldtlsRepo.findByOwnerId(201)).thenReturn(optional);
		VehicleDetails  details = vehicleDetailsService.findVehicleByOwnerId(201);
		assertNotNull(details);
		if(details.getVhicleDtlsId()==null) {
			fail();
		}
	}
	
	@Test
	public void findVehicleByOwnerIdTestNegative() {
		Optional<VehicleDetailsEntity> optional=Optional.empty();
		when(vchldtlsRepo.findById(201)).thenReturn(optional);
		VehicleDetails vehicleDetails2 = vehicleDetailsService.findVehicleByOwnerId(201);
		assertEquals(new VehicleDetails(), vehicleDetails2);
	}
	@Test
	public void saveVehicleDetailsTestPositive() {
		Optional<VehicleOwnerDtlsEntity> optional=Optional.of(new VehicleOwnerDtlsEntity());
		VehicleDetailsEntity detailsEntity=new VehicleDetailsEntity();
		BeanUtils.copyProperties(vehicleDetails, detailsEntity);
		when(ownerDtlsRepo.findById(101)).thenReturn(optional);
		when(vchldtlsRepo.save(detailsEntity)).thenReturn(new VehicleDetailsEntity() {{ setVhicleDtlsId(201);}});
		Integer vhclId=vehicleDetailsService.saveVehicleDetails(vehicleDetails, 101);
		assertEquals(201, vhclId);
		
	}
	
	@Test
	public void saveVehicleDetailsTestNegative() {
		Optional<VehicleOwnerDtlsEntity> optional=Optional.empty();
		when(ownerDtlsRepo.findById(101)).thenReturn(optional);
		Integer vhclId=vehicleDetailsService.saveVehicleDetails(vehicleDetails, 101);
		assertNull(vhclId);
	}
	
	
	
	
	
	
	
	
}
