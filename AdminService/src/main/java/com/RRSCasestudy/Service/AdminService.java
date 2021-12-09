package com.RRSCasestudy.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.RRSCasestudy.Resource.AdminController;
import com.RRSCasestudy.model.TrainAvailability;
import com.RRSCasestudy.model.User;
@RestController()
@RequestMapping("/admin")
public class AdminService {
	@Autowired
	RestTemplate restTemplate;
	AdminController admin;
	@Autowired
	public AdminService(AdminController admin) {
		this.admin= admin;
	}
	//-------------------Train Info Adding----------------------//
	  @PostMapping("/Add") 
		public String add(@RequestBody TrainAvailability train)
		{
			  return admin.add(train);
		}
		
	//-------------Display all the train Details----------------------// 
	@GetMapping("/")
	public List<TrainAvailability> consume1()
	{
		return admin.callForDisplaying();
	}
	
	//-------------Search by Source location the train Details----------------------// 
	@GetMapping("/searchBySource1/{startLocation}")
	public List<TrainAvailability> searchby(@PathVariable ("startLocation") String startLocation)
	{
		return admin.SeearchBySource(startLocation);
	}
	
	//-------------Search by Destination location the train Details----------------------// 
	@GetMapping("/searchByDetination/{destination}")
	public List<TrainAvailability> searchByDestination(@PathVariable ("destination") String destination)
	{
		 return admin.SeearchByDestination(destination);
		//return new ResponseEntity<>(admin.SeearchByDestination(destination),HttpStatus.OK);
	}
	
	//--------------------Updating Train Info By ADMIN------------------------------------//
	@PutMapping("/updateByAdmin")
	public String updateTrainInfo(@RequestBody TrainAvailability train)
	{
		return admin.UpdateTrainInfoByAdmin(train);
	}
	
	//--------------------Delete train Info By Admin-----------------------------//
	
	//------------------------Display All users-------------------------------
	@GetMapping("/allUsers")
	public List<User> getUsers()
	{
		return admin.getAllUser();
	}
	
	//---------------------------Delete User-------------------------
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable int userId)
	{
	  return admin.deleteByAdmin(userId);
	}
}
