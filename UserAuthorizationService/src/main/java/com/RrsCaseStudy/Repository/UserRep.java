package com.RrsCaseStudy.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.RrsCaseStudy.Model.UserModel;

public interface UserRep extends MongoRepository<UserModel, String> {
	UserModel findByUsername(String username);
	
}
