package com.transport.rto.web.exception.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * this used global exception handler for 
 * any type of exception comes in our web application or c2B application 
 * side  
 * 
 * @author Rituraj
 *
 */
@ControllerAdvice
@Controller
public class WebAppExceptionHandler {
	/**
	 * this use to capable handle all exception in web application
	 * 
	 * @return String
	 */
	@ExceptionHandler(Exception.class)
	public String  ErrorPage() {
		
		return "errorPage";
	}

}
