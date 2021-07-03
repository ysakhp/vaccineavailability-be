package com.vaccine.availability.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.availability.model.ResponsePojo;
import com.vaccine.availability.model.User;
import com.vaccine.availability.service.SequenceService;
import com.vaccine.availability.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceService sequenceService;
	
	@PostMapping(value = { "/register"})
	public User registerUser(@RequestBody User user) throws Exception {
		
//		user.setId(sequenceService.getSequenceNumber(User.SEQUENCE_NAME));
		return userService.saveUser(user);
	}
	
	@GetMapping(value = { "/", "" })
	public List<User> getUsers(){
		logger.info("Get Users");
		return userService.getUsers();
	}
	
	//http://localhost:8081/api/users/1
	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable(value = "id") String id) {
		logger.info("Get User"+id);
		return userService.getUser(id);
	}
	
	//http://localhost:8081/api/users/email/akh@gmail.com
	@GetMapping("/email/{email}")
	public Optional<User> getUserByEmail(@PathVariable(value = "email") String email) {

		return userService.getUserByEmail(email);
	}
	
	@PostMapping("/otp")
	public ResponseEntity<ResponsePojo> generateOtp(@RequestBody String email){
	
		return userService.generateOtp(email);
	}
	
	@PostMapping("/resetpassword")
	public  ResponseEntity<ResponsePojo> resetPassword(@RequestBody User user){
		logger.info("Reset Password");
		return userService.resetPassword(user);
	}
	
	
}
