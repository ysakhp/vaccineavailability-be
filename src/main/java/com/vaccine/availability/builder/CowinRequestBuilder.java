package com.vaccine.availability.builder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaccine.availability.model.Center;
import com.vaccine.availability.model.CenterList;

@Service
public class CowinRequestBuilder {

	Logger log = LoggerFactory.getLogger(CowinRequestBuilder.class);

	private static final String COWIN_API_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?";

	public List<Center> getCowinDetails(Integer pincode, String date) throws IOException, InterruptedException {

		log.info("Getting Cowin details from COWIN API ");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().header("accept", "application/json").GET()
				.uri(URI.create(COWIN_API_URL + "pincode=" + pincode + "&date=" + date)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		log.info(COWIN_API_URL + "pincode=" + pincode + "&date=" + date + response.body().toString());

		ObjectMapper mapper = new ObjectMapper();

		CenterList centerList = mapper.readValue(response.body(), CenterList.class);

		log.info("Center List " + centerList);

		return centerList.getCenters();
	}

}
