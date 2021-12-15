package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.utils.JwtUtils;

@RestController

public class AdminAuthController {

	
	
	@Autowired
	AdminRepository userrepo;
	
	@Autowired
	AuthenticationManager authenticates;
	
	@Autowired
	AdminService userservice;
	
	@Autowired
	JwtUtils jwtutil;
	
	@PostMapping("/subs")
	private ResponseEntity<AuthenticationResponse>subscribeClient(@RequestBody AuthenticationRequest authreq){
		AdminModel usermodel =new AdminModel();
//		UserModel oldusermodel=new UserModel();
//		oldusermodel=userrepo.findByusername(authreq.getUsername());
//		try {
//			
//			if (oldusermodel.getUsername() == authreq.getUsername()) {
//				throw new Exception("Username already present");
//			}
//					
//		}
//		catch(Exception e) {
//			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
//					("Username must be unique ") , HttpStatus.OK);
//		}
		
		usermodel.setUsername(authreq.getUsername());
		usermodel.setPassword(authreq.getPassword());
		
		
		try {
			userrepo.save(usermodel);
		}
		catch(Exception e){
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
					("Error during subscription ") , HttpStatus.OK);
		}
		
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
				("Successful subs for client " +authreq.getUsername()), HttpStatus.OK);
//		return ResponseEntity.ok(new AuthenticationResponse());
	}
	
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authreq){
		String username=authreq.getUsername();
		String password= authreq.getPassword();
		try {
			authenticates.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				
		}
		catch(Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse(" Invalid Credentials..!"));
		}
		
		UserDetails userdetails= userservice.loadUserByUsername(username);
		
		String jwt = jwtutil.generateToken(userdetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@Autowired
	RestTemplate restTemplate;
	AdminService admin;
	@Autowired
	public AdminAuthController(AdminService admin) {
		this.admin= admin;
	}
	//-------------------Train Info Adding----------------------//
	  @PostMapping("/Add") 
		public String add(@RequestBody TrainAvailability train)
		{
			  return admin.add(train);
		}
		
	//-------------Display all the train Details----------------------// 
	@GetMapping("/gettrains")
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
	
	@DeleteMapping("/DeleteTrain/{id}")
	public String deleteTrainInfo(@PathVariable int id)
	{
		return admin.deleteTrain(id);
	}
		
	//-------------------Displaying all Reservation--------
	@GetMapping("/getreservation")
	public List<Reservation> getOrder()
	{
		return admin.getReservation();
	}
	

	//---------------------------Delete Reservation-------------------------
	@DeleteMapping("/deleteReservation/{rId}")
	public String deleteReservation(@PathVariable int rId)
	{
	  return admin.deleteReservationByAdmin(rId);
	}
	
	
	
}
