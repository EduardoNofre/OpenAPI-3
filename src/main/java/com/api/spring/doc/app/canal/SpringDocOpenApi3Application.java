package com.api.spring.doc.app.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Micro Service estudo SpringDoc + OpenAPI 3 ", version = "1.0",description = " Estudo rapido "))

public class SpringDocOpenApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocOpenApi3Application.class, args);
	}
}
