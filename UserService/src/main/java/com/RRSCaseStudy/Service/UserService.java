package com.RRSCaseStudy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.RRSCaseStudy.Model.TrainAvailability;
import com.RRSCaseStudy.Resource.UserController;

@RestController
@RequestMapping("/users")
public class UserService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	UserController user;
	public UserService(UserController user) {
		this.user=user;
	}
	//-------------Display all the train Details----------------------// 
		@GetMapping("/")
		public List<TrainAvailability> consume1()
		{
			return user.callForDisplaying();
		}
}
