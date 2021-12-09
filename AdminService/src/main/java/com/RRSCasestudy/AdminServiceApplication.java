package com.RRSCasestudy;

import java.io.IOException;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class AdminServiceApplication {

	public static void main(String[] args) throws RestClientException,IOException {
		SpringApplication.run(AdminServiceApplication.class, args);
		
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
