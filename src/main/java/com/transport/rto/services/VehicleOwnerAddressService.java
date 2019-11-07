package com.transport.rto.services;

import com.transport.rto.model.VehicleOwnerAddress;

/**
 * 
 * this is used to provide opertion details for VehicleOwnerAddress UI
 * @author Rituraj
 *
 */
public interface VehicleOwnerAddressService {
	/**
	 * This method is used to store data and return regNum as output
	 * 
	 * @param addr
	 * @param ownerId
	 * @return Integer
	 */
	public Integer saveOwnerAddr(VehicleOwnerAddress addr,Integer ownerId);
	/**
	 * fetching the data from VehicleOwnerAddress related table based on foreign key  which is a 
	 * primary key for owner dtls table 
	 * @param id
	 * @return VehicleOwnerAddress
	 */
	public VehicleOwnerAddress findAddrbyOwnerId(Integer id);
	
	/**
	 * 
	 * fetching the data from VehicleOwnerAddress related table based on primary key  which is a 
	 * primary key for owner dtls table 
	 * 
	 * @param addrId
	 * @return VehicleOwnerAddress
	 */
	public VehicleOwnerAddress findAddrbyAddrId(Integer addrId);
}