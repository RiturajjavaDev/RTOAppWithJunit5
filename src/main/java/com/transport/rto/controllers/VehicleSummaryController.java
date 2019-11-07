package com.transport.rto.controllers;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transport.rto.constants.AppConstant;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.model.VehicleRegistrationDtls;
import com.transport.rto.properties.AppProperties;
import com.transport.rto.services.VchlRegistrationDetailsService;
import com.transport.rto.services.VehicleDetailsService;
import com.transport.rto.services.VehicleOwnerAddressService;
import com.transport.rto.services.VehicleOwnerDetailsService;

/**
 * this Controller is used to handle request for VehicleSummary after filling
 * all form we need show the summary page with all data
 * 
 * @author Rituraj
 *
 */
@Controller
public class VehicleSummaryController{
	
	
	/**
	 * enable logging for this class 
	 */
	private static final Logger log=LoggerFactory.getLogger(VehicleSummaryController.class);
	/**
	 * inject VchlRegistrationDetailsService for fetching registration related data
	 */
	@Autowired
	private VchlRegistrationDetailsService vhclRegservice;
	/**
	 * inject VehicleDetailsService for fetching vehicle Details related data
	 */
	@Autowired
	private VehicleDetailsService vhclDetailService;
	/**
	 * inject VehicleOwnerDetailsService for fetching vehicleowner dtls related data
	 */
	@Autowired
	private VehicleOwnerDetailsService vhclOwnerDtls;
	/**
	 * inject VehicleOwnerAddressService for fetching vehicleOwnerAddrress related
	 * data
	 */
	@Autowired
	private VehicleOwnerAddressService vhclAddrDts;
	/**
	 * inject AppProperties to fetch msg which is available in cache
	 */
	@Autowired
	private AppProperties props;
	/**
	 * 
	 * this method is used after form submition completed to review your data so
	 * that we collect all data and return in view page
	 * 
	 * @param vhcldtlsId
	 * @param model
	 * @return String
	 * @throws MessagingException
	 */
	@RequestMapping("/summary")
	public String getAllDetails(@RequestParam("vhclId") Integer vhcldtlsId, Model model) throws MessagingException {
		log.info("*****GetAllDetails  method  for VHCLSummaryController  Starts******");
		VehicleDetails vehicleDetails = vhclDetailService.getVehicleDetails(vhcldtlsId);
		Integer vhclOwnerid = vehicleDetails.getDtlsEntity().getVhclOwnerid();
		VehicleOwnerDetails ownerDetails = vhclOwnerDtls.findById(vhclOwnerid);
		VehicleOwnerAddress address = vhclAddrDts.findAddrbyOwnerId(vhclOwnerid);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_OWNER_ADDR_DTLS, address);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_OWNER_DTLS, ownerDetails);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_VEHICLE_DTLS, vehicleDetails);
		log.info("*****GetAllDetails  method  for VHCLSummaryController  Ends******");
		return AppConstant.LOGICAL_VHCL_SUMMARY_VIEW;
	}

	@RequestMapping("/register")
	public String registerSubmit(Model model, @RequestParam("ownerId") Integer ownerkey,
			@RequestParam("center") String center, @RequestParam("vchltype") String vchltype) throws Exception {
		log.info("*****registerSubmit  method  for VHCLSummaryController  Starts******");
		VehicleRegistrationDtls registration = vhclRegservice.registration(ownerkey, center, vchltype);
		String msg = props.getMessages().get(AppConstant.MODEL_ATTR_SUCC_MSG);
		model.addAttribute(AppConstant.MODEL_ATTR_SUCC_MSG_KEY, msg);
		model.addAttribute(AppConstant.MODEL_ATTR_REGS_KEY, registration);
		log.info("*****registerSubmit  method  for VHCLSummaryController  Ends******");
		return AppConstant.LOGICAL_VHCL_REGIS_VIEW;
	}
}
