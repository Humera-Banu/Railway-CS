package com.RrsCaseStudy.Resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.RrsCaseStudy.Model.AuthenticationRequest;
import com.RrsCaseStudy.Model.AuthenticationResponse;
import com.RrsCaseStudy.Model.TrainAvailability;
import com.RrsCaseStudy.Model.UserModel;
import com.RrsCaseStudy.Repository.UserRep;
import com.RrsCaseStudy.Model.Reservation;
import com.RrsCaseStudy.Service.UserService;
import com.RrsCaseStudy.Utils.JwtUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	UserRep repository;
	@Autowired
	AuthenticationManager authenticates;
	@Autowired
	UserService userService;
	@Autowired
	JwtUtils JwtUtils;

	@PostMapping("/reg")
	private ResponseEntity<?> subscribe(@RequestBody AuthenticationRequest request)
	{
		
		String username = request.getUsername();
		String password = request.getPassword();
		UserModel model = new UserModel();
		model.setUsername(username);
		model.setPassword(password);
		
		try {
			repository.save(model);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error while subsribing the user with username " + username));
		}
		return ResponseEntity.ok(new AuthenticationResponse("client subscribed with username " + username));
	}
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request)
	{
		String username = request.getUsername();
		String password = request.getPassword();
		try {
			authenticates.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error while authenticating" + username));
		}
		//return ResponseEntity.ok(new AuthenticationResponse("Succesfull authentication for user " + username));
		UserDetails loadedUser = userService.loadUserByUsername(username);
		String generatedToken = JwtUtils.generateToken(loadedUser);
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*")
				.exposedHeaders("Authorization").allowCredentials(true);
			}
		};
	}
	
	@GetMapping("/hi")
	public String Hi() {
		return "hello";
	}
	
//---------------------User Operation On Train Service---------------------------	
//-------------Display all the train Details----------------------// 
	@GetMapping("/")
	public List<TrainAvailability> consume1()
	{
		return userService.callForDisplaying();
	}
	
	//-------------Search by Source location the train Details----------------------// 
	@GetMapping("/searchBySource1/{startLocation}")
	public List<TrainAvailability> searchby(@PathVariable ("startLocation") String startLocation)
	{
		return userService.SeearchBySource(startLocation);
	}
	
	//-------------Search by Destination location the train Details----------------------// 
	@GetMapping("/searchByDetination/{destination}")
	public List<TrainAvailability> searchByDestination(@PathVariable ("destination") String destination)
	{
		 return userService.SeearchByDestination(destination);
		//return new ResponseEntity<>(admin.SeearchByDestination(destination),HttpStatus.OK);
	}
	//------------------------User operation On Reservation Service-----------
			//-----------------Adding Reservation--------------
			@PostMapping("/Add") 
			public Reservation add(@RequestBody Reservation order)
			{
				  return userService.add(order);
			}
			//-------------------Displaying all Reservation--------
			@GetMapping("/getreservation")
			public List<Reservation> getOrder()
			{
				return userService.getReservation();
			}
			

			//---------------------------Delete Reservation-------------------------
			@DeleteMapping("/deleteReservation/{rId}")
			public String deleteReservation(@PathVariable int rId)
			{
			  return userService.deleteReservationByUser(rId);
			}
	
	
	@GetMapping("/getUsers")
	public List<UserModel> getUsers()
	{
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/deleteuser")
	public String delete(@RequestBody UserModel user)
	{
		userService.deleteUser(user);
		return "User Deleted";
	}
	
}
