package com.RRSCasestudy.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.RRSCasestudy.model.Reservation;
import com.RRSCasestudy.model.TrainAvailability;
import com.RRSCasestudy.model.User;


@RestController

public class AdminController {
	
	RestTemplate restTemplate;
	@Autowired
	public AdminController(RestTemplateBuilder restTemplateBuilder) {
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
	
	//!!!!!!!!!!!!!!-----Admin Authorization to User Service-----!!!!!!!!!!!
	//---------------View All Users-------------------
	public List<User> getAllUser()
	{
		return restTemplate.exchange("http://localhost:8087/users/ShowAllUsers",HttpMethod.GET,null,List.class).getBody();
	}
	
	//-----------------------Delete All Users--------------------------
	@DeleteMapping("/{userId}")
	public String deleteUserByAdmin(@PathVariable int userId)
	{
		return restTemplate.exchange("http://localhost:8087/users/delete/"+userId,HttpMethod.DELETE,null,String.class).getBody();
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
