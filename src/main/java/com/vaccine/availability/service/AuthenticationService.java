package com.vaccine.availability.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vaccine.availability.model.AuthRequestPojo;
import com.vaccine.availability.model.AuthResponsePojo;
import com.vaccine.availability.util.JwtUtil;

@Service
public class AuthenticationService {
	
	Logger log = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	public ResponseEntity<AuthResponsePojo> createToken(AuthRequestPojo authReq) throws Exception {

		try {
			log.info("try authentication");
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
		} catch (BadCredentialsException e) {
			log.info("Exception "+ e);
			throw new BadCredentialsException("Invalid username password", e);
		}

		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authReq.getEmail());

		final String JWToken = jwtUtil.generateToken(userDetails);
		
		

		log.info("Token "+JWToken +"User Id "+customUserDetailsService.getLoggedInUserId());
		return ResponseEntity.ok(new AuthResponsePojo(JWToken,customUserDetailsService.getLoggedInUserId()));

	}

}
