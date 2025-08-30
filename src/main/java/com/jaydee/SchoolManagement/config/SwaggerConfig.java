package com.jaydee.SchoolManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpneAPI() {
		return new OpenAPI()
				.info(new Info().title("School API")
						.description("API Documentation for School Application")
						.version("v1.0"));
	}
	
	//http://localhost:8080/api/swagger-ui.html
}
