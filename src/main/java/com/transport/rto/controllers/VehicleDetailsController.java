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

import com.transport.rto.constants.AppConstant;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.services.VehicleDetailsService;

/**
 * this Controller use to handle the request which comes from VehicleDetails Ui
 * and also send the data from VehicleDetails Ui
 * 
 * @author Rituraj
 */
@Controller
public class VehicleDetailsController {
	/**
	 * Enable logger for this class
	 */
	private static final Logger log=LoggerFactory.getLogger(VehicleDetailsController.class);
	/**
	 * inject VehicleDetailsService to send data for database
	 */
	@Autowired
	private VehicleDetailsService service;

	/**
	 * we use for handle request when come with previous button and also populate
	 * that page when fresh request comes
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@RequestMapping("/vchlIndex")
	public String index(HttpServletRequest req, Model model) {
		log.info("******vhcl dtls index method starts******");
		VehicleDetails details = null;
		Integer ownerId = null;
		Integer addrId=null;
		String vhclId = req.getParameter(AppConstant.REQ_ATTR_FOR_VHCL_KEY);
		String ownerKey = req.getParameter(AppConstant.REQ_ATTR_FOR_OWNER_KEY);
		String addrkey=req.getParameter(AppConstant.REQ_ATTR_FOR_ADDR_KEY);
		if (ownerKey != null) {
			ownerId = Integer.parseInt(ownerKey);
		} else {
			ownerId = (Integer) model.getAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK);
		}
		if(addrkey!=null) {
			addrId = Integer.parseInt(addrkey);
		} else {
			addrId = (Integer) model.getAttribute(AppConstant.MODEL_ATTR_KEY_FOR_ADDR_PK);
		}
		if (vhclId != null && !vhclId.equals("")) {
			details = service.getVehicleDetails(Integer.parseInt(vhclId));
		} else {
			details = new VehicleDetails();
		}
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK, addrId);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_FOR_ADDR_PK, ownerId);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_VEHICLE_DTLS, details);
		log.info("******vhcl dtls index method Ends******");
		return AppConstant.LOGICAL_VEHICLE_DTLS_VIEW;
	}

	/**
	 * this method is used to capture the data VehicleDetails Ui and service layer
	 * to store data in database and redirect to next screen like summary page
	 * 
	 * @param details
	 * @param ownKey
	 * @param vhclId
	 * @return String
	 */
	@RequestMapping(value = "/saveVehicleDtls", method = RequestMethod.POST)
	public String handleSubmitButton(@ModelAttribute("vehicleDtls") VehicleDetails details,
			@RequestParam("ownerId") Integer ownKey, @RequestParam("vehicleId") Integer vhclId) {
		log.info("******vhcl dtls handleNextBtn method starts******");
		if (vhclId != null) {
			details.setVhicleDtlsId(vhclId);
		}
		Integer vehicleDetails = service.saveVehicleDetails(details, ownKey);
		log.info("******vhcl dtls handleNextBtn method Ends******");
		return AppConstant.REDIRECT_VEHICLE_SUMMARY + vehicleDetails;
	}

}
