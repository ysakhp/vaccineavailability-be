package com.vaccine.availability.model;
/**
 * 
 * @author Vaisakh
 *
 */
public class AuthResponsePojo {
	
	private final String token;
	private final String user;

	
	public AuthResponsePojo(String token, String user) {
		this.token = token;
		this.user = user;
	}


	public String getToken() {
		return token;
	}


	public String getUser() {
		return user;
	}
	
	

}
