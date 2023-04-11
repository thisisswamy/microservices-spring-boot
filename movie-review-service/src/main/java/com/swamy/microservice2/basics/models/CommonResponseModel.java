package com.swamy.microservice2.basics.models;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseModel {
	
	private String message;

	public CommonResponseModel(String message) {
		super();
		this.message = message;
	}

	public CommonResponseModel() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
