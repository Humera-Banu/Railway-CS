package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.AdminModel;
import com.example.demo.model.Reservation;
import com.example.demo.model.TrainAvailability;
import com.example.demo.model.UserModel;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService{
	
	@Autowired
	AdminRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminModel foundedUser=userrepo.findByusername(username);
		if (foundedUser==null) {
			return null;
		}
		String user=foundedUser.getUsername();
		String pass=foundedUser.getPassword();
		return new User(user, pass,new ArrayList<>());
	}
		

	RestTemplate restTemplate;
	@Autowired
	public AdminService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate= restTemplateBuilder.build();
	}
	//!!!!!!!!!-----------Admin Operation On train Service--------!!!!!!!!!!
	//-------------------Train Info Adding----------------------//
	public String add(TrainAvailability train)
	{
		HttpEntity<TrainAvailability> entity = new HttpEntity<>(train);
		return restTemplate.exchange("http://localhost:8082/train/AddTrains",HttpMethod.POST,entity,String.class).getBody();
		
	}
	//-------------Display all the train Details----------------------// 
	public List<TrainAvailability> callForDisplaying()
	{
		return restTemplate.exchange("http://localhost:8082/train/ShowAllTrains",HttpMethod.GET,null,List.class).getBody();
		
		  //return restTemplate.getForObject("http://localhost:8002/train-availability-service/train/ShowAllTrains",List.class);	
	}
	//-------------Search by Source location the train Details----------------------// 
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
	
	//--------------------Updating Train Info By ADMIN------------------------------------//
	public String UpdateTrainInfoByAdmin(TrainAvailability train)
	{
		HttpEntity<TrainAvailability> entity = new HttpEntity<>(train);
		return restTemplate.exchange("http://localhost:8082/train/updateTraindetails",HttpMethod.PUT,entity,String.class).getBody();
		
	}
	
	//--------------------------Delete Train Info By Admin-----------------/delete/{id}
	@DeleteMapping("/{id}")
	public String deleteTrain(@PathVariable int id)
	{
		return restTemplate.exchange("http://localhost:8082/train/delete/"+id,HttpMethod.DELETE,null,String.class).getBody();
	}
	
	//------------------Admin Operation on Reservation service------------
	//-----------------View All Reservation----------------------
	
	public List<Reservation> getReservation()
	{
		return restTemplate.exchange("http://localhost:8083/reservation/getall",HttpMethod.GET,null,List.class).getBody();
	}
	
	//------------------Delete Reservation---------------------
	
	@DeleteMapping("{/rId}")
	public String deleteReservationByAdmin(@PathVariable int rId)
	{
		return restTemplate.exchange("http://localhost:8083/reservation/Delete/"+rId,HttpMethod.DELETE,null,String.class).getBody();
	}
}
