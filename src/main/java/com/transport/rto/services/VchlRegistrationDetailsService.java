package com.transport.rto.services;


import com.transport.rto.model.VehicleRegistrationDtls;
/**
 * 
 * this is used to provide abstract operation for VchlRegistrationDetailsService 
 * @author Rituraj
 *
 */
public interface VchlRegistrationDetailsService {
	/**
	 * this is used to done the registration 
	 * if all the comes with validness 
	 * 
	 * @param OwnerKey
	 * @param center
	 * @return VehicleRegistrationDtls
	 */
	public VehicleRegistrationDtls registration(Integer OwnerKey,String center,String vtype) throws Exception;
	
	/**
	 * this method take registration number as input parameter
	 * and provide  Registration details 
	 * @param regNum
	 * @return VehicleRegistrationDtls
	 */
	public VehicleRegistrationDtls findbyRegNum(String regNum);

}
