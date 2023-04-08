package com.swamy.microservice2.basics.models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class UserResponse {

	@Override
	public String toString() {
		return "UserResponse [userName=" + userName + ", emailAddress=" + emailAddress + "]";
	}
	private String userName;
	private String emailAddress;
	
	
	public UserResponse() {
		super();
	}
	public UserResponse(String userName, String emailAddress) {
		super();
		this.userName = userName;
		this.emailAddress = emailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
