package com.transport.rto.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * this class is used to as cache like on startup of application all the message which
 * is avilable in yaml file store in map object and provide those message to other clasess 
 * 
 * @author Rituraj
 *
 */
@Component
@ConfigurationProperties(prefix = "form-app")
@Data
public class AppProperties {
	/**
	 * this is used like a cache
	 */
	private Map<String, String> messages;
}
