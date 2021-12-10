package com.RrsCaseStudy.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.RrsCaseStudy.model.TrainAvailability;

public interface RrsRepository extends MongoRepository<TrainAvailability,Integer> {

	List<TrainAvailability> findByDestination(String destination);

	List<TrainAvailability> findByStartLocation(String startLocation);

	List<TrainAvailability> findByAvailableDate(Date availableDate);

}
