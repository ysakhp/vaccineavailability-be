package com.vaccine.availability.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaccine.availability.controller.UserController;
import com.vaccine.availability.model.User;
import com.vaccine.availability.repository.UserRepository;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Transactional
	public User saveUser(User user) throws Exception {
		logger.info("Saving user" + user);

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new Exception("User already exist");
		}

		return userRepository.save(user);
	}

	public List<User> getUsers() {
		logger.info("Getting all user");
		return userRepository.findAll();
	}

	public Optional<User> getUser(String id) {
		logger.info("Find user" + id);
		return userRepository.findById(id);
	}

	public Optional<User> getUserByEmail(String email) {
		logger.info("Find by email " + email);
		return userRepository.findAll().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findAny();
	}

}
