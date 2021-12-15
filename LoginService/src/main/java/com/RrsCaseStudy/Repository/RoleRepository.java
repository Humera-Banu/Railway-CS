package com.RrsCaseStudy.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.RrsCaseStudy.model.ERole;
import com.RrsCaseStudy.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}