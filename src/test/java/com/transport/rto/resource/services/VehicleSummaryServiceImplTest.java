package com.transport.rto.resource.services;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.model.VehicleRegistrationDtls;
import com.transport.rto.resource.binding.VehicleSummary;
import com.transport.rto.resource.services.impl.VehicleSummaryServiceImpl;
import com.transport.rto.rest.resources.exce.handler.NoDataFoundException;
import com.transport.rto.services.VchlRegistrationDetailsService;
import com.transport.rto.services.VehicleDetailsService;
import com.transport.rto.services.VehicleOwnerAddressService;
import com.transport.rto.services.VehicleOwnerDetailsService;

@SpringBootTest
public class VehicleSummaryServiceImplTest {

	@InjectMocks
	private VehicleSummaryServiceImpl summaryService;
	@Mock
	private VchlRegistrationDetailsService  vhclRegservice;
	@Mock
	private VehicleDetailsService  vhclDetailService;
	@Mock
	private VehicleOwnerDetailsService vhclOwnerDtls;
	@Mock
	private VehicleOwnerAddressService vhclAddrDts;
	
	
	@Test
	public  void findVehicleDetailsTestPositive() throws Exception {
		VehicleRegistrationDtls registrationDtls=new VehicleRegistrationDtls() {{
			setVehicleRegNumber("EE99IRR");
			setDtlsEntity(new VehicleOwnerDtlsEntity() {{
				setVhclOwnerid(101);
			}});
		}};
		when(vhclRegservice.findbyRegNum("EE99IRR")).thenReturn(registrationDtls);
		when(vhclDetailService.findVehicleByOwnerId(101)).thenReturn(new VehicleDetails());
		when(vhclAddrDts.findAddrbyOwnerId(101)).thenReturn(new VehicleOwnerAddress());
		when(vhclOwnerDtls.findById(101)).thenReturn(new VehicleOwnerDetails());
		VehicleSummary vehicleSummary = summaryService.findVehicleDetails("EE99IRR");
		assertNotNull(vehicleSummary);
		if(vehicleSummary.getRegDlts().getVehicleRegNumber()==null) {
			fail();
		}
	}
	@Test
	public void findVehicleDetailsTestNegative() throws Exception {
		VehicleRegistrationDtls dtls=new VehicleRegistrationDtls();
		when(vhclRegservice.findbyRegNum("EE99IRR")).thenReturn(dtls);
		assertThrows(NoDataFoundException.class, () -> {
			summaryService.findVehicleDetails("EE99IRR");
		});
		
	
	} 
	
}
