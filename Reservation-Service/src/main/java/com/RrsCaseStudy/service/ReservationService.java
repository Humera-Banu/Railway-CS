package com.RrsCaseStudy.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RrsCaseStudy.Repository.ReservationRepository;
import com.RrsCaseStudy.model.Reservation;
import com.RrsCaseStudy.model.TrainAvailability;
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

	public void deleteOrder(int rId) {
		repository.deleteById(rId);
	//	return "Reservation cancelled for Id " + rId;
	}

	public void delete(Reservation order)
	{
		repository.delete(order);
	}

	public Reservation addR(Reservation order, TrainAvailability train) {
		order.setTicketFair(train.getTicketFair()*order.getPassengers());
		order.setStartLocation(train.getStartLocation());
		return	repository.insert(order);
	}

	public Optional<Reservation> getOrderById(int rId) {
		return repository.findById(rId);
	}

	
//	public Reservation add(Reservation order, TrainAvailability train) {
//		order.setTrainName(train.getTrainName());
////		order.setStartLocation(train.getStartLocation());
////		order.setDestination(train.getDestination());
//		order.setTrainNo(train.getTrainNo());
//		return	repository.insert(order);
//	}
}
