package com.vaccine.availability.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaccine.availability.controller.UserController;
import com.vaccine.availability.model.ResponsePojo;
import com.vaccine.availability.model.User;
import com.vaccine.availability.repository.UserRepository;
import com.vaccine.availability.util.UserUtils;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ResponsePojo responsePojo;

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

	public ResponseEntity<ResponsePojo> generateOtp(String email) {

		String otp = UserUtils.generateRandomNumber();
		

		try {

			getUserByEmail(email).ifPresentOrElse(user -> {

				logger.info("Email present");

				user.setOtp(otp);
				userRepository.save(user);

				emailService.buildEmail().setToEmail(email)
						.setContent("Hi,\nOTP for resetting password is " + otp + " ")
						.setSubject("OTP for Password Reset").send();
				responsePojo.setStatus(true);
				

			}, () -> {
				
				responsePojo.setStatus(false);

			});
		} catch (Exception e) {
			logger.error("Exception generating otp" + e.getMessage());
			responsePojo.setStatus(false);;
		}

		return ResponseEntity.ok(responsePojo);
	}

	public ResponseEntity<ResponsePojo> resetPassword(User user) {

		try {
			getUserByEmail(user.getEmail()).ifPresentOrElse(userModel -> {

				if (user.getOtp().equals(userModel.getOtp()) && !userModel.getOtp().isEmpty()) {
					userModel.setPassword(user.getPassword());
					userModel.setOtp(null);
					userRepository.save(userModel);
					responsePojo.setStatus(true);
				}

			},()->{
				responsePojo.setStatus(false);
			});

		} catch (Exception e) {
			logger.error("Exception resetting password" + e.getMessage());
			responsePojo.setStatus(false);
		}

		return ResponseEntity.ok(responsePojo);
	}

}
