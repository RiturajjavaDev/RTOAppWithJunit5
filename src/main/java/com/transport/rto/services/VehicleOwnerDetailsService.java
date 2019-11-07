package com.transport.rto.services;

import com.transport.rto.model.VehicleOwnerDetails;
/**
 * this is used to provide rules for VehicleOwnerDetails Ui
 * 
 * @author Rituraj
 *
 */
public interface VehicleOwnerDetailsService {

	/**
	 * this method is used to save the data which comes from VehicleOwnerDetails UI
	 * and store in database 
	 * 
	 * @param details
	 * @return Integer
	 */
	public Integer saveOwnerDtls(VehicleOwnerDetails details);
	/**
	 * this method is used to fetch the data from owner dtls table 
	 * when user use previous hyperlink 
	 * or
	 * when rest api calls happen
	 * or
	 * for association mapping calling time
	 * @param id
	 * @return
	 */
	public VehicleOwnerDetails findById(Integer id);
	
}
