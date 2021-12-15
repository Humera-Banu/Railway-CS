package com.RrsCaseStudy.Service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.RrsCaseStudy.Model.Reservation;
import com.RrsCaseStudy.Model.TrainAvailability;
import com.RrsCaseStudy.Model.UserModel;
import com.RrsCaseStudy.Repository.UserRep;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRep repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userFounded=repository.findByUsername(username);
		if (userFounded == null) {
			return null;
		}
		String name = userFounded.getUsername();
		String pwd = userFounded.getPassword();
		return new User(name, pwd,new ArrayList<>());
	}
	
	@Autowired
	RestTemplate restTemplate;
	 public UserService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
		//User Operation------------------
	 
		 //-----------Get All users-----------------
		 public List<UserModel> getAllUsers() {
				return repository.findAll();
			}
		 
		 //Delete USer
		 public void deleteUser(UserModel user) {
		     repository.delete(user);
			
		}
		
	//---------------User Operation On Train Service------------------------	
		//------------------Viewing all the available Trains
		public List<TrainAvailability> callForDisplaying()
		{
			return restTemplate.exchange("http://localhost:8082/train/ShowAllTrains",HttpMethod.GET,null,List.class).getBody();
			
			  //return restTemplate.getForObject("http://localhost:8002/train-availability-service/train/ShowAllTrains",List.class);	
		}
			
		@RequestMapping("/{startLocation}")
		public List<TrainAvailability> SeearchBySource(@PathVariable("startLocation") String startLocation)
		{
			return restTemplate.exchange("http://localhost:8082/train/findtrainBySource/"+startLocation,HttpMethod.GET,null,List.class).getBody();
			//return restTemplate.getForObject("http://localhost:8002/train-availability-service/train/findtrainBySource/"+startLocation, List.class);
		}
		
		//-------------Search by Destination location the train Details----------------------// 
		@RequestMapping("/{destination}")
		public List<TrainAvailability> SeearchByDestination(@PathVariable("destination") String destination)
		{
			return restTemplate.exchange("http://localhost:8082/train/findtrainByDestination/"+destination,HttpMethod.GET,null,List.class).getBody();
			//return restTemplate.getForObject("http://localhost:8002/train-availability-service/train/findtrainByDestination/"+destination, List.class);
		}
		//---------------------user Operation on reservation----------------------
		//--------------------Add Reservation--------------------
		public Reservation add(@RequestBody Reservation order)
		{
			HttpEntity<Reservation> entity = new HttpEntity<>(order);
			return restTemplate.exchange("http://localhost:8083/reservation/addorder",HttpMethod.POST,entity,Reservation.class).getBody();
			
		}
		//-----------------View All Reservation----------------------
		
			public List<Reservation> getReservation()
			{
				return restTemplate.exchange("http://localhost:8083/reservation/getall",HttpMethod.GET,null,List.class).getBody();
			}
			
			//------------------Delete Reservation---------------------
			
			@DeleteMapping("{/rId}")
			public String deleteReservationByUser(@PathVariable int rId)
			{
				return restTemplate.exchange("http://localhost:8083/reservation/Delete/"+rId,HttpMethod.DELETE,null,String.class).getBody();
			}

			
}
