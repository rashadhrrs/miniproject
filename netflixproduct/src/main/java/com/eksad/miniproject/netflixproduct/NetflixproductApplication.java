package com.eksad.miniproject.netflixproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication

@EnableCircuitBreaker
public class NetflixproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixproductApplication.class, args);
	}

}
