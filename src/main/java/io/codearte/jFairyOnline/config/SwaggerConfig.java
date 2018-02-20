package io.codearte.jFairyOnline.config;

import java.util.Collections;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("io.codearte.jFairyOnline.rest"))
				.paths(regex("/rest.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"JFairyOnline REST API",
				"Spring Boot REST API for JFairyOnline test data generator",
				"1.0",
				"Terms of service",
				new springfox.documentation.service.Contact("Olga Maciaszek-Sharma",
						"http://olgamaciaszek.github.io/", ""),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}