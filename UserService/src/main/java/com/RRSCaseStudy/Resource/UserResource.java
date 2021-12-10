package com.RRSCaseStudy.Resource;

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

import com.RRSCaseStudy.Model.User;
import com.RRSCaseStudy.Repository.UserRepository;



@RestController
@RequestMapping("/user")
public class UserResource {
	@Autowired
	UserRepository userRepository;
	@PostMapping("/AddUser")
	public String AddTrain(@RequestBody User user)
	{
		userRepository.save(user);
		return "User Details added with UserID "+ user.getUserId();
	}
	@GetMapping("/ShowAllUsers")
	public List<User> getTrains()
	{
		return userRepository.findAll();
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
	public String UpdateTrain(@RequestBody User user)
	{
		userRepository.save(user);
		return "User Details updated with UserID "+ user.getUserId();
	}
	
	@DeleteMapping("/delete/{userId}")
	public String deleteTrainByID(@PathVariable int userId)
	{
		userRepository.deleteById(userId);
		return "User info deleted with userid "+ userId;
	}
}
