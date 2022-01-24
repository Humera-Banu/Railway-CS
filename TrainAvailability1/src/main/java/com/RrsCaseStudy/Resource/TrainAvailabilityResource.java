package com.RrsCaseStudy.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RrsCaseStudy.Repository.RrsRepository;
import com.RrsCaseStudy.model.TrainAvailability;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/train")
public class TrainAvailabilityResource {
	@Autowired
	RrsRepository repository;
	@PostMapping("/AddTrains")
	public TrainAvailability AddTrain(@RequestBody TrainAvailability train)
	{
		return  repository.save(train);
		
	}
	@GetMapping("/ShowAllTrains")
	public List<TrainAvailability> getTrains()
	{
		return repository.findAll();
	}
	@GetMapping("/findtrainById/{trainNo}")
	public Optional<TrainAvailability> getByID(@PathVariable int trainNo)
	{
		return repository.findById(trainNo);
	}
	@GetMapping("/findtrainByDestination/{destination}")
	public List<TrainAvailability> getTrainByDestination(@PathVariable String destination)
	{
		return repository.findByDestination(destination);
	}
	@GetMapping("/findtrainBySource/{destination}")
	public List<TrainAvailability> getTrainByStartLocation(@PathVariable String startLocation)
	{
		return repository.findByStartLocation(startLocation);
	}
	@GetMapping("/findtrainByDate/{availableDate}")
	public List<TrainAvailability> getTrainByAvailableDate(@PathVariable Date availableDate)
	{
		return repository.findByAvailableDate(availableDate);
	}
	@PutMapping("/updateTraindetails/{trainNo}")
	public TrainAvailability UpdateTrain(@RequestBody TrainAvailability train,@PathVariable int trainNo)
	{
		return repository.save(train);
		
	}
	@DeleteMapping("/delete/{trainNo}")
	public void deleteTrainByID(@PathVariable int trainNo)
	{
		 repository.deleteById(trainNo);
		//return "Train info deleted at trainNo "+ trainNo;
	}
	@DeleteMapping("/deleteTrain")
	public TrainAvailability deleteTrain(@RequestBody TrainAvailability train)
	{
		 repository.delete(train);
		return train;
	}
	
	
}
