package com.ericsson.modernization.services.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class  ProductCatalogServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Docker World";
	}
}
