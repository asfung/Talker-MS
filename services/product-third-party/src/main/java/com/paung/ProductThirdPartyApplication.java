package com.paung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductThirdPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductThirdPartyApplication.class, args);
	}

}
