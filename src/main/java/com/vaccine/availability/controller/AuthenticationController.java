package com.vaccine.availability.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.availability.model.AuthRequestPojo;
import com.vaccine.availability.model.AuthResponsePojo;
import com.vaccine.availability.service.AuthenticationService;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
	Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;

	
	@PostMapping
	public ResponseEntity<AuthResponsePojo> createAuthenticationToken(@RequestBody AuthRequestPojo authReq) throws Exception{
		log.info("Authentication API" + authReq);
		return authenticationService.createToken(authReq);
	}
}
