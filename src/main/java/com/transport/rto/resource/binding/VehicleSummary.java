package com.transport.rto.resource.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.transport.rto.model.VehicleDetails;
import com.transport.rto.model.VehicleOwnerAddress;
import com.transport.rto.model.VehicleOwnerDetails;
import com.transport.rto.model.VehicleRegistrationDtls;

import lombok.Data;
/**
 *This class used as Binding Class for Resource and getting support for xml and json format here we all 
 *pojo refrence to collect all the data and return one model as response for restClient   
 *
 * @author Rituraj
 *
 */
@Data
@XmlRootElement(name ="vehicle-summary")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"registration-details","owner-detials","owner-address","vechicle-details"})
public class VehicleSummary{
	/**
	 * this is used to get all vehicle registration related dtls
	 */
	@XmlElement(name="registration-details")
	private VehicleRegistrationDtls regDlts;
	/**
	 * this is used to get all vehicle owner related dtls
	 */
	@XmlElement(name="owner-detials")
	private VehicleOwnerDetails ownerDetails;
	/**
	 * this is used to get all vehicle  owner address related dtls
	 */
	@XmlElement(name="owner-address")
	private VehicleOwnerAddress ownerAddr;
	/**
	 * this is used to get all vehicle related dtls
	 */
	@XmlElement(name="vechicle-details")
	private VehicleDetails vhclDtls;
	
}
