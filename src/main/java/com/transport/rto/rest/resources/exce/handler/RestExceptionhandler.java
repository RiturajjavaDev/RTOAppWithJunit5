package com.transport.rto.rest.resources.exce.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * this class is used to global exception handler for rest api
 * @author Rituraj
 *
 */
@RestControllerAdvice
public class RestExceptionhandler {

	/**
	 * enable  logging for {@link RestExceptionhandler}  class
	 */
	private static final Logger log=LoggerFactory.getLogger(RestExceptionhandler.class);
	/**
	 * this method  is used to provide error response when exception occurred in rest api  
	 * @return
	 */
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ApiError> excehandle(){
		log.info("***** Exception Occured in Rest Api starts*****");	
		ApiError apiError=new ApiError();
		apiError.setErrorCode(400);
		apiError.setErrorMsg("Unable to find details for this Reg. Number");
		apiError.setDate(new Date());
		log.info("***** Exception Occured in Rest Api ends*****");
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> otherExcehandle(){
		log.info("***** Exception Occured in Rest Api starts*****");	
		ApiError apiError=new ApiError();
		apiError.setErrorCode(400);
		apiError.setErrorMsg("Try some Time data Not Found");
		apiError.setDate(new Date());
		log.info("***** Exception Occured in Rest Api ends*****");
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	
	
}
