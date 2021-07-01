package com.vaccine.availability.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Center {

	int center_id;
	private String name;
	private List<Session> sessions;


	
	public int getCenter_id() {
		return center_id;
	}
	public void setCenter_id(int center_id) {
		this.center_id = center_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	@Override
	public String toString() {
		return "Center [center_id=" + center_id + ", name=" + name + ", sessions=" + sessions + "]";
	}
	
	
	
	
	
	
	
	
}
