package com.transport.rto.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transport.rto.entity.VehicleOwnerDtlsEntity;

import lombok.Data;

/**
 * this class used to recieve the data from Vehicle details UI
 * when then request comes in our application
 * and also used refrence of that class in vehicle summary
 * 
 * @author Rituraj
 *
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleDetails {

	private Integer vhicleDtlsId;
	private String vehicleType;
	private Integer mfdYear;
	private String companyName;
	private String color;
	@XmlTransient
	@JsonIgnore
	private VehicleOwnerDtlsEntity dtlsEntity;	
}
