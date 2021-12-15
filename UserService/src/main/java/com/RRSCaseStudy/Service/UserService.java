package com.RRSCaseStudy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.RRSCaseStudy.Model.Reservation;
import com.RRSCaseStudy.Model.TrainAvailability;
import com.RRSCaseStudy.Model.User;
import com.RRSCaseStudy.Repository.UserRepository;
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
	//------------------------User Operation on user Service------------
		@Autowired
		UserRepository userRepository;
		@PostMapping("/AddUser")
		public User AddUser(@RequestBody User user)
		{
			return userRepository.save(user);
			
		}
		@GetMapping("/ShowAllUsers")
		public List<User> getUsers()
		{
			List<User> user = userRepository.findAll();
			System.out.println("Data is "+user);
			return user;
			
		}
		
		@GetMapping("/findUserById/{userId}")
		public Optional<User> getByID(@PathVariable int userId)
		{
			return userRepository.findById(userId);
		}
		
		@GetMapping("/findUserByName/{userName}")
		public Optional<User> getByName(@PathVariable String userName)
		{
			return userRepository.findByUserName(userName);
		}
		
		@PutMapping("/updateUserdetails")
		public User UpdateUser(@RequestBody User user)
		{
			return userRepository.save(user);
			 
		}
		@DeleteMapping("/delete")
		public User deleteUser(@RequestBody User user)
		{
			userRepository.delete(user);
			return user;
		}
		
		@DeleteMapping("/delete/{userId}")
		public String deleteUserByID(@PathVariable int userId)
		{
			userRepository.deleteById(userId);
			return "User info deleted with userid "+ userId;
		}
		
//---------------------User Operation On Train Service---------------------------	
	//-------------Display all the train Details----------------------// 
		@GetMapping("/")
		public List<TrainAvailability> consume1()
		{
			return user.callForDisplaying();
		}
		
		//-------------Search by Source location the train Details----------------------// 
		@GetMapping("/searchBySource1/{startLocation}")
		public List<TrainAvailability> searchby(@PathVariable ("startLocation") String startLocation)
		{
			return user.SeearchBySource(startLocation);
		}
		
		//-------------Search by Destination location the train Details----------------------// 
		@GetMapping("/searchByDetination/{destination}")
		public List<TrainAvailability> searchByDestination(@PathVariable ("destination") String destination)
		{
			 return user.SeearchByDestination(destination);
			//return new ResponseEntity<>(admin.SeearchByDestination(destination),HttpStatus.OK);
		}
		
//------------------------User operation On Reservation Service-----------
		//-----------------Adding Reservation--------------
		@PostMapping("/Add") 
		public String add(@RequestBody Reservation order)
		{
			  return user.add(order);
		}
		//-------------------Displaying all Reservation--------
		@GetMapping("/getreservation")
		public List<Reservation> getOrder()
		{
			return user.getReservation();
		}
		

		//---------------------------Delete Reservation-------------------------
		@DeleteMapping("/deleteReservation/{rId}")
		public String deleteReservation(@PathVariable int rId)
		{
		  return user.deleteReservationByAdmin(rId);
		}
}
