package com.transport.rto.resource.services;

import com.transport.rto.resource.binding.VehicleSummary;

/**
 * this is used provide service rules for resource api 
 * @author Rituraj
 *
 */
public interface VehicleSummaryServices {
	/**
	 * this method take input as vehicle number and return all details 
	 * related to vehicle
	 * 
	 * @param regNum
	 * @return VehicleSummary
	 */
	public VehicleSummary findVehicleDetails(String regNum)throws Exception;
}
