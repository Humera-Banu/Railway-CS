package com.RrsCaseStudy.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RrsCaseStudy.Repository.ReservationRepository;
import com.RrsCaseStudy.model.Reservation;
import com.RrsCaseStudy.resource.ReservationResource;
@Service
public class ReservationService {
	@Autowired
	ReservationRepository repository;

	public List<Reservation> getOrder() {
		return repository.findAll();
	}

	public Reservation addReservation(Reservation order) {
		return repository.insert(order);
	}

	public Reservation update(Reservation order) {
		return repository.save(order);
	}

	public String deleteOrder(int rId) {
		repository.deleteById(rId);
		return "Reservation cancelled for Id " + rId;
	}

	
}
