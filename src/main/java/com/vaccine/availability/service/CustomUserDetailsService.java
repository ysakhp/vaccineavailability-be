package com.vaccine.availability.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaccine.availability.model.User;
import com.vaccine.availability.repository.UserRepository;

/**
 * 
 * @author Vaisakh
 * User Detail Service for Web Security
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	private String userId;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user != null)
		userId = user.getId();
		return  new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(),new ArrayList<>());
	}
	
	public String getLoggedInUserId() {
		return userId;
	}

}
