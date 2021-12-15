package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringSecurityMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityMongoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

//Important points

// not able to sign up using same username
// password validation
//make sure jwt expires after 30 mins
//role based authorization using jwt
//email validation