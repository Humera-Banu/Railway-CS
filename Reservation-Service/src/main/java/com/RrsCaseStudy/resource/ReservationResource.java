package com.RrsCaseStudy.resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
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

import com.RrsCaseStudy.model.Reservation;
import com.RrsCaseStudy.model.TrainAvailability;
import com.RrsCaseStudy.service.ReservationService;

import jdk.internal.vm.annotation.ReservedStackAccess;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/reservation")
public class ReservationResource {
	
	@Autowired
	ReservationService service;
	@Autowired
	RestTemplate restTemplate;
	public ReservationResource(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
	@PostMapping("/addorder")
	public Reservation addReservation(@RequestBody Reservation order)
	{	
		
		return service.addReservation(order);
	}
	
//	@PostMapping("/Add")
//	public Reservation add(@RequestBody Reservation order)
//	{
//		int trainNo = order.getTrainNo();
//		System.out.println(trainNo);
//		TrainAvailability train = restTemplate.exchange("http://localhost:8082/train/findtrainById/"+ trainNo,HttpMethod.GET,null,TrainAvailability.class).getBody();
//		return service.add(order,train);
//	}
	@PostMapping("/add")
	public Reservation add(@RequestBody Reservation order)
	{
		String destination = order.getDestination();
	System.out.println(destination);
	TrainAvailability train = restTemplate.exchange("http://localhost:8082/train//findtrainByDestination/"+destination,HttpMethod.GET,null,TrainAvailability.class).getBody();
	return service.addR(order,train);
	}
//	
	@GetMapping("/getall")
	public List<Reservation> getOrder()
	{
		return service.getOrder();
	}
	@GetMapping("get/{rId}")
	public Optional<Reservation> getOrderById(@PathVariable int rId){
		return service.getOrderById(rId);
	}
	@PutMapping("/update")
	public Reservation updatereservation(@RequestBody Reservation order)
	{
		return service.update(order);
	}
	@DeleteMapping("/Delete/{rId}")
	public void deleteOrder(@PathVariable("rId") int rId)
	{
		 service.deleteOrder(rId);
	}
	@DeleteMapping("/Delete")
	public Reservation delete(@RequestBody Reservation order)
	{
		 service.delete(order);
		 return order;
	}
	
}
