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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.transport.rto.constants.AppConstant;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.services.VehicleOwnerAddressService;

/**
 * this Controller is used to handle request which comes for Address related UI
 * 
 * @author Rituraj
 *
 */
@Controller
public class VehicleOwnerAddressController {

	/**
	 * Enable logging for this class
	 */
	private static  final Logger log=LoggerFactory.getLogger(VehicleOwnerAddressController.class);
	/**
	 * inject service to send the data and get data from database
	 */
	@Autowired
	private VehicleOwnerAddressService service;

	/**
	 * this method is used to show the page with or without data based on condition
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/addrindex")
	public String index(Model model, HttpServletRequest req) {
		log.info("*****Index method  for VHCLOwnerAddressController  starts******");
		Integer ownerId = null;
		String addrId = null;
		VehicleOwnerAddress address = null;
		addrId = req.getParameter(AppConstant.REQ_ATTR_FOR_ADDR_KEY);

		ownerId = (Integer) model.getAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK);
		if (addrId != null && !(addrId.equals(""))) {
			address = service.findAddrbyAddrId(Integer.parseInt(addrId));
			String ownerId1 = req.getParameter(AppConstant.REQ_ATTR_FOR_OWNER_KEY);
			ownerId = Integer.parseInt(ownerId1);
		} else {

			address = new VehicleOwnerAddress();
		}
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK, ownerId);
		model.addAttribute(AppConstant.MODEL_ATTR_KEY_OWNER_ADDR_DTLS, address);
		log.info("*****Index method  for VHCLOwnerAddressController  Ends******");
		return AppConstant.LOGICAL_OWNER_ADDR_VIEW;
	}

	/**
	 * 
	 * this method is used to capture the data and also used to redirect the page
	 * from address to vehicle dtls page
	 * 
	 * @param address
	 * @param request
	 * @param redirect
	 * @return String
	 */
	@RequestMapping(value = "/handleNext", method = RequestMethod.POST)
	public String handleNextBtn(@ModelAttribute("ownerAddr") VehicleOwnerAddress address, HttpServletRequest request,
			RedirectAttributes redirect) {
		log.info("*****HandleNextBtn  method  for VHCLOwnerAddressController  starts******");
		String ownerId = request.getParameter(AppConstant.REQ_ATTR_FOR_OWNER_KEY);
		String addrid = request.getParameter(AppConstant.REQ_ATTR_FOR_ADDR_KEY);
		if (addrid != null && !addrid.equals("")) {
			address.setAddressId(Integer.parseInt(addrid));
		}
		Integer addrId = service.saveOwnerAddr(address, Integer.parseInt(ownerId));

		redirect.addFlashAttribute(AppConstant.MODEL_ATTR_KEY_FOR_OWNER_PK, Integer.parseInt(ownerId));
		redirect.addFlashAttribute(AppConstant.MODEL_ATTR_KEY_FOR_ADDR_PK, addrId);
		log.info("*****HandleNextBtn  method  for VHCLOwnerAddressController  Ends******");
		return AppConstant.REDIRECT_VEHICLE_DTLS;
	}

}