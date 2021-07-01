package com.vaccine.availability.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaccine.availability.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmail(String email);

}
