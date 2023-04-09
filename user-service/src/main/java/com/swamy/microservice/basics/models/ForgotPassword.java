package com.swamy.microservice.basics.models;

import org.springframework.stereotype.Component;

@Component
public class ForgotPassword {
	
	private String email;

	public ForgotPassword() {
		super();
	}

	public ForgotPassword(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotPassword [email=" + email + "]";
	}
	

}
