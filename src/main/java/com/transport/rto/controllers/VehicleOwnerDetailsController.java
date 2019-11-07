package com.transport.rto.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.transport.rto.constants.AppConstant;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.services.VehicleOwnerDetailsService;

/**
 * this Controller is used to handle request comes for VehicleOwnerDetails
 * related Ui
 * 
 * @author Rituraj
 *
 */
@Controller
public class VehicleOwnerDetailsController {
	
	/**
	 * enable logging for this controller
	 */
	private static final Logger log=LoggerFactory.getLogger(VehicleOwnerDetailsController.class);
	
	/**
	 * inject VehicleOwnerDetails service to perform business operation
	 */
	@Autowired
	private VehicleOwnerDetailsService service;

	/**
	 * this is used to populate the initial form when the first request comes in our
	 * application with binding object
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/ownerForm")
	public String index(Model model, HttpServletRequest req) {
		log.info("*****index  method  for VHCLOwnerDtlsController  Starts******");
		String ownkey = (String) req.getParameter(AppConstant.REQ_ATTR_FOR_OWNER_KEY);
		VehicleOwnerDetails ownerdetails = null;
		if (ownkey != null && !(ownkey.equals(""))) {
			ownerdetails = service.findById(Integer.parseInt(ownkey));
		} else {
			ownerdetails = new VehicleOwnerDetails();
		}
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_OWNER_DTLS, ownerdetails);
		log.info("*****index  method  for VHCLOwnerDtlsController  Ends******");
		return AppConstant.LOGICAL_OWNER_DTLS_VIEW;
	}

	/**
	 * 
	 * use to capture owner dtls from ui and after that redirect to address page
	 * 
	 * @param ownerDtls
	 * @param ownerid
	 * @param attributes
	 * @return String
	 */
	@RequestMapping(value = "/saveDtls", method = RequestMethod.POST)
	public String handleNextButton(@ModelAttribute("vehicleOwnerDtls") VehicleOwnerDetails ownerDtls,
			@RequestParam("ownerId") Integer ownerid, RedirectAttributes attributes) {
		log.info("*****HandleNextBtn  method  for VHCLOwnerDtlsController  Starts******");	
		if (ownerid != null && ownerid > 0) {
			ownerDtls.setVhclOwnerid(ownerid);
		}
		Integer vehicleOwnerKey = service.saveOwnerDtls(ownerDtls);
		attributes.addFlashAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK, vehicleOwnerKey);
		log.info("*****HandleNextBtn  method  for VHCLOwnerDtlsController  Ends******");
		return AppConstant.REDIRECT_VEHICLE_ADDR;
	}
}