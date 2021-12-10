package com.RrsCaseStudy.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.RrsCaseStudy.model.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation,Integer> {
	

}
