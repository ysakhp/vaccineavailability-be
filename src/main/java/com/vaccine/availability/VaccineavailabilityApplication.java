package com.vaccine.availability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaccineavailabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineavailabilityApplication.class, args);
	}

}
