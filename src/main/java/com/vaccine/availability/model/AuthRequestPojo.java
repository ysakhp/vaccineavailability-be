package com.vaccine.availability.model;

import java.io.Serializable;

/**
 * 
 * @author Vaisakh
 * 
 *         For User authentication
 *
 */
public class AuthRequestPojo implements Serializable {

	private String email;
	private String password;

	public AuthRequestPojo() {}

	public AuthRequestPojo(String email, String password) {

		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequestPojo [email=" + email + ", password=" + password + "]";
	}

	
}
