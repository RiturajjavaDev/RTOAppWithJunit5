package com.transport.rto.rest.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transport.rto.resource.binding.VehicleSummary;
import com.transport.rto.resource.services.VehicleSummaryServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * this is a Rest Api to provide B2B communication to 
 * other applications here provide vehicle number and get
 * all details related to vehichle
 * 
 * @author Rituraj
 *
 */
@RestController
@RequestMapping("/rest")
@Api(value="For getting Vechicle Details ", description = "This api used to get th deatils related to vechicle")
public class VehicleRestController {
	
	/**
	 * enable Logger for {@link VehicleRestController} class
	 */
	private static final Logger log=LoggerFactory.getLogger(VehicleRestController.class);
	
	
	/**
	 * to Inject Service object for perform bussiness opertion
	 */
	@Autowired
	private VehicleSummaryServices service;
	
	/**
	 * this operation take one path parameter like vehicle number and provide details 
	 * in json or xml format
	 * 
	 * @param regNum
	 * @return VehicleSummary
	 */
	@ApiOperation(value = "This operation  all Details  related to vechicle",response = ResponseEntity.class)
	@GetMapping(value="/findVehicleDetails/{vno}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<VehicleSummary> getVehicleDetails 	(@ApiParam(value = "send Your vehicle Number as path parameter",required = true) @PathVariable("vno") String regNum)
	throws Exception
	{
		log.info("getVehicleDetails from rest api request is starts");
		VehicleSummary vehicleSummary = service.findVehicleDetails(regNum);
		log.info("getVehicleDetails from rest api request is ends");
		return new ResponseEntity<VehicleSummary>(vehicleSummary, HttpStatus.OK);
	}
	
}
