package com.RRSCaseStudy.Resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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




@RestController
public class UserController {
	@Autowired
	RestTemplate restTemplate;
	public UserController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
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
	public String add(Reservation order)
	{
		HttpEntity<Reservation> entity = new HttpEntity<>(order);
		return restTemplate.exchange("http://localhost:8083/reservation/addorder",HttpMethod.POST,entity,String.class).getBody();
		
	}
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
