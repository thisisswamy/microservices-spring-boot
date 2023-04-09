package com.swamy.microservice.basics.models;

import org.springframework.stereotype.Component;

@Component
public class CustomHttpResponse {
	
	private String message;
	
	public CustomHttpResponse() {
		super();
	}

	public CustomHttpResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
