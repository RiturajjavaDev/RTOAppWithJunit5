package com.transport.rto.service.impl;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.rto.entity.VehicleOwnerDtlsEntity;
import com.transport.rto.entity.VehicleRegistrationEntity;
import com.transport.rto.mail.sender.MailSenderUtil;
import com.transport.rto.model.VehicleRegistrationDtls;
import com.transport.rto.repositories.VchlRegistrationRepository;
import com.transport.rto.repositories.VhclOwnerDtlsRepository;
import com.transport.rto.services.impl.VchlRegistrationDetailsServiceImpl;

@SuppressWarnings("deprecation")
@SpringBootTest
public class VchlRegistrationDetailsServiceImplTest {

	@InjectMocks
	private VchlRegistrationDetailsServiceImpl vchlRegService;

	@Mock
	private VchlRegistrationRepository regDtlsRepo;

	@Mock
	private MailSenderUtil mailSender;
	@Mock
	private VhclOwnerDtlsRepository ownerRepo;

	private static VehicleRegistrationEntity entity;

	@BeforeAll
	public static void beforeTest() {
		entity = new VehicleRegistrationEntity();
		entity.setRegCenter("Hyderabad");
		entity.setRegDate(new Date());
	}

	@Test
	public void findbyRegNumTestNegative() {
		final String regNum = "RE34VCD";
		Optional<VehicleRegistrationEntity> regDtls = Optional.empty();
		when(regDtlsRepo.findRegDtls(regNum)).thenReturn(regDtls);
		VehicleRegistrationDtls registrationDtls = vchlRegService.findbyRegNum(regNum);
		assertEquals(new VehicleRegistrationDtls(), registrationDtls);
	}

	@Test
	public void findbyRegNumTestPositive() {
		final String regNum = "RE34VCD";
		entity.setVehicleRegNumber(regNum);
		Optional<VehicleRegistrationEntity> regDtls = Optional.of(entity);
		when(regDtlsRepo.findRegDtls(regNum)).thenReturn(regDtls);
		VehicleRegistrationDtls regNum2 = vchlRegService.findbyRegNum(regNum);
		assertEquals(regNum, regNum2.getVehicleRegNumber());

	}

	@Test
	public void registrationTestPositive() throws Exception {
		final Integer ownerId = 101;
		final String regCenter = "Hyderabad";
		final String vhclType = "4-Wheeler";
		final String regNum = "JJ999LLL";
		String string = anything().toString();
		Optional<VehicleOwnerDtlsEntity> ownerDtls = Optional.of(new VehicleOwnerDtlsEntity());
		when(ownerRepo.findById(101)).thenReturn(ownerDtls);
		when(mailSender.sendMail(string,string,string,string)).thenReturn(true);
		when(regDtlsRepo.save(Matchers.any(VehicleRegistrationEntity.class)))
				.thenReturn(new VehicleRegistrationEntity() {
					{
						setVehicleRegId(101);
						setVehicleRegNumber(regNum);
					}
				});
		VehicleRegistrationDtls registrationDtls = vchlRegService.registration(ownerId, regCenter, vhclType);
		assertNotNull(registrationDtls);
		assertEquals(regNum, registrationDtls.getVehicleRegNumber());
	}

	@Test
	public void registrationTestNegative() throws Exception {
		Optional<VehicleOwnerDtlsEntity> ownerDtls = Optional.empty();
		when(ownerRepo.findById(301)).thenReturn(ownerDtls);
		VehicleRegistrationDtls registrationDtls = vchlRegService.registration(301, "", "");
		assertEquals(new VehicleRegistrationDtls(), registrationDtls);

	}

}
