package com.swamy.microservice.basics.JWT;

import org.springframework.stereotype.Component;

@Component
public class JwtToken {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtToken() {
		super();
	}

	public JwtToken(String token) {
		super();
		this.token = token;
	}
	

}
