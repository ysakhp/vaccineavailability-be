package com.vaccine.availability.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.vaccine.availability.model.ResponsePojo;

@Configuration
public class CoreBeans {
	
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ResponsePojo getResponsePojo() {
		return new ResponsePojo();
	}

}
