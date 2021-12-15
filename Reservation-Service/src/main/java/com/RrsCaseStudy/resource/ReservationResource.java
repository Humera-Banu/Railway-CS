package com.RrsCaseStudy.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

import com.RrsCaseStudy.model.Reservation;
import com.RrsCaseStudy.service.ReservationService;

@RestController
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
	@GetMapping("/getall")
	public List<Reservation> getOrder()
	{
		return service.getOrder();
	}
	@PutMapping("/update")
	public Reservation updatereservation(@RequestBody Reservation order)
	{
		return service.update(order);
	}
	@DeleteMapping("/Delete/{rId}")
	public String deleteOrder(@PathVariable("rId") int rId)
	{
		return service.deleteOrder(rId);
	}
	@DeleteMapping("/Delete")
	public Reservation delete(@RequestBody Reservation order)
	{
		 service.delete(order);
		 return order;
	}
}
