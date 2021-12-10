package com.RRSCaseStudy.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.RRSCaseStudy.Model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

}
