package com.transport.rto.configs;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is class to provide swagger support to our application 
 * it provide both swagger UI as well as Swagger documnetation
 * 
 * @author Rituraj
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * THis is used to create Docket Object to show the swagger ui
	 * provide the location of Rest Controllers 
	 * @return Docket
	 */
	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(basePackage("com.transport.rto.rest.resources")).paths(any()).build().apiInfo(apiInfo1());

		return docket;
	}
	/**
	 * This is used to provide producer Details
	 * @return
	 */
	private ApiInfo apiInfo1() {
		ApiInfo builder = new ApiInfoBuilder().title("RITURAJ TECH SOLUTION Pvt.Ltd").description("User Apllication ")
				.contact(new Contact("Rituraj Rawat", "https://github.com/rawatrituraj653", "Rituraj4@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
		return builder;

	}
}