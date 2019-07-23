package com.eksad.miniproject.netflixproduct.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

	@EnableSwagger2
	@Configuration
	@Import(BeanValidatorPluginsConfiguration.class)
	public class SwaggerConfig {
		
		@Bean
		public Docket eksadAPI() {
			return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.miniproject.netflixproduct"))
				.paths(regex("/.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
					new Tag("Product", "Product Management API"),
					new Tag("Get Data API", ""),
					new Tag("Data Manipulation API","")
				);
		}
		
		public ApiInfo metaInfo() {
			ApiInfo apiInfo = new ApiInfo(
			"Hystrix Data Management REST API ",
			"Rest API Collection for Employee Data Management",
			"1.0.0",
			"http://google.com",
			new Contact ("Rashad Harris","http://www.rashad.com","rashadhrrs@gmail.com"),
			"Rashad 2.0",
			"http://www.google.com/license",
			Collections.emptyList()
			);
			
			return apiInfo;
		}

	}






