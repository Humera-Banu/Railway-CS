package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdminModel;

@Repository
public interface AdminRepository extends MongoRepository<AdminModel, String>{

		AdminModel findByusername(String username);
	
}
