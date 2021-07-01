package com.vaccine.availability.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CenterList {
	private List<Center> centers;
	
	public CenterList() {
		centers = new ArrayList<Center>();
	}

	public List<Center> getCenters() {
		return centers;
	}

	public void setCenters(List<Center> centers) {
		this.centers = centers;
	}

	@Override
	public String toString() {
		return "CenterList [centers=" + centers + "]";
	}
	

}
