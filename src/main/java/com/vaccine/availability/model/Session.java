package com.vaccine.availability.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
	String session_id;
	int min_age_limit;
	int available_capacity;
	int available_capacity_dose1;
	int available_capacity_dose2;
	
	String date;
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public int getAvailable_capacity() {
		return available_capacity;
	}
	public void setAvailable_capacity(int available_capacity) {
		this.available_capacity = available_capacity;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMin_age_limit() {
		return min_age_limit;
	}
	public void setMin_age_limit(int min_age_limit) {
		this.min_age_limit = min_age_limit;
	}
	public int getAvailable_capacity_dose1() {
		return available_capacity_dose1;
	}
	public void setAvailable_capacity_dose1(int available_capacity_dose1) {
		this.available_capacity_dose1 = available_capacity_dose1;
	}
	public int getAvailable_capacity_dose2() {
		return available_capacity_dose2;
	}
	public void setAvailable_capacity_dose2(int available_capacity_dose2) {
		this.available_capacity_dose2 = available_capacity_dose2;
	}
	@Override
	public String toString() {
		return "Session [session_id=" + session_id + ", min_age_limit=" + min_age_limit + ", available_capacity="
				+ available_capacity + ", available_capacity_dose1=" + available_capacity_dose1
				+ ", available_capacity_dose2=" + available_capacity_dose2 + ", date=" + date + "]";
	}
	
	
	

}
