package com.transport.rto.rest.resources.exce.handler;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
/**
 * 
 * this class is used to provide error response when the Exception occured in rest api
 * @author Rituraj
 *
 */
@Data
@XmlRootElement(name = "api-erorr")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiError {

	@XmlElement(name="error-code")
	private Integer errorCode;
	@XmlElement(name="error-msg")
	private String errorMsg;
	private Date date;
}
