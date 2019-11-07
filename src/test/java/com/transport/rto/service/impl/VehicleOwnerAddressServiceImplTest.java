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

import com.transport.rto.entity.VehicleOwnerAddrEntity;
import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.repositories.VhclOwnerAddrRepositories;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.impl.VehicleOwnerAddressServiceImpl;
/**
 * this method is used to test VehicleOwnerAddressServiceImpl service class
 * @author Rituraj
 *
 */
@SpringBootTest
public class VehicleOwnerAddressServiceImplTest {
	
	/**
	 * inject object for which class we need to test we can say target object
	 */
	@InjectMocks
	private VehicleOwnerAddressServiceImpl ownerAddrService;
	/**
	 * inject dependent mock object for Address repo
	 */
	@Mock
	private VhclOwnerAddrRepositories addRepo;
	/**
	 * inject depedent mock object  for owner Repo
	 */
	@Mock
	private VhclOwnerDtlsRepository ownerDtlsRepo;
	/**
	 * refrence for model object to transfer the data
	 */
	private static VehicleOwnerAddress address;
	/**
	 * this mehtod is used to set the data for VehicleOwnerAddress refrenece before all
	 * test case run
	 */
	@BeforeAll
	public  static void beforeAllTest() {
		address=new VehicleOwnerAddress();
		address.setCity("Chhatarpur");
		address.setHouseNo("9A-77");
		address.setStreetName("HMDA");
		address.setZip(34544);
		address.setDtlsEntity(new VehicleOwnerDtlsEntity());
	}
	
	/**
	 *this test case is used to test negative senario for findAddrbyAddrid
	 *if owner data is not there then how method is behave
	 */
	@Test
	public void findAddrbyAddrIdTestNegative() {
		Optional<VehicleOwnerAddrEntity> addrEntity =Optional.empty();
		when(addRepo.findById(101)).thenReturn(addrEntity);
		VehicleOwnerAddress addr = ownerAddrService.findAddrbyAddrId(101);
		assertEquals(new VehicleOwnerAddress(), addr);
	}
	
	
	/**
	 *this test case is used to test positive senario for findAddrbyAddrid
	 *if owner data is not there then how method is behave
	 */
	@Test
	public void findAddrbyAddrIdTestPositive() {
		VehicleOwnerAddrEntity addrEntity=new  VehicleOwnerAddrEntity();
		BeanUtils.copyProperties(address,addrEntity);
		addrEntity.setAddressId(101);
		Optional<VehicleOwnerAddrEntity> opt=Optional.of(addrEntity);
		when(addRepo.findById(101)).thenReturn(opt);
		VehicleOwnerAddress address1 = ownerAddrService.findAddrbyAddrId(101);
		assertNotNull(address1);
		if(address1.getAddressId()==null)fail();
		
	}
	
	
	@Test
	public void findAddrbyOwnerIdTestNegative() {
		Optional<VehicleOwnerAddrEntity> addrEntity =Optional.empty();
		when(addRepo.findByOwnerId(201)).thenReturn(addrEntity);
		VehicleOwnerAddress addr = ownerAddrService.findAddrbyOwnerId(201);
		assertEquals(new VehicleOwnerAddress(), addr);
	}
	
	@Test
	public void findAddrbyOwnerIdTestPositive() {
		VehicleOwnerAddrEntity addrEntity=new  VehicleOwnerAddrEntity();
		BeanUtils.copyProperties(address,addrEntity);
		addrEntity.setAddressId(101);
		Optional<VehicleOwnerAddrEntity> opt=Optional.of(addrEntity);
		when(addRepo.findByOwnerId(201)).thenReturn(opt);
		VehicleOwnerAddress address1 = ownerAddrService.findAddrbyOwnerId(201);
		assertNotNull(address1);
		if(address1.getAddressId()==null) {
			fail();
		}	
	}
	
	@Test
	public void saveOwnerAddrTestNegative() {
		Optional<VehicleOwnerDtlsEntity> optional=Optional.empty();
		when(ownerDtlsRepo.findById(101)).thenReturn(optional);
		Integer addId = ownerAddrService.saveOwnerAddr(address, 101);
		assertNull(addId);
	}
	
	@Test
	public void saveOwnerAddrTestPositve() {
		Optional<VehicleOwnerDtlsEntity> optional=Optional.of(new VehicleOwnerDtlsEntity());
		VehicleOwnerAddrEntity addrEntity=new VehicleOwnerAddrEntity();
		BeanUtils.copyProperties(address, addrEntity);
		when(ownerDtlsRepo.findById(101)).thenReturn(optional);
		when(addRepo.save(addrEntity)).thenReturn(new VehicleOwnerAddrEntity() {{
			setAddressId(201);
		}});
		Integer addId = ownerAddrService.saveOwnerAddr(address, 101);
		
		assertEquals(201,addId);
	}	
	
}

