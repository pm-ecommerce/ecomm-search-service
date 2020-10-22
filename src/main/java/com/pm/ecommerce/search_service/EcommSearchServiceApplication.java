package com.pm.ecommerce.search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.pm.ecommerce.entities"})
public class EcommSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommSearchServiceApplication.class, args);
	}

}