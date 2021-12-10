package com.RRSCaseStudy.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.RRSCaseStudy.Model.TrainAvailability;



@RestController
public class UserController {
	@Autowired
	RestTemplate restTemplate;
	public UserController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
	public List<TrainAvailability> callForDisplaying()
	{
		return restTemplate.exchange("http://localhost:8082/train/ShowAllTrains",HttpMethod.GET,null,List.class).getBody();
		
		  //return restTemplate.getForObject("http://localhost:8002/train-availability-service/train/ShowAllTrains",List.class);	
	}

}
