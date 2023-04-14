package com.swamy.microservice.basics.models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class UserResponse {

	
	private String userName;
	private String emailAddress;
	private String displayName;
	
	public UserResponse() {
		super();
	}
	
	
	
	public UserResponse(String userName, String emailAddress, String displayName) {
		super();
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.displayName = displayName;
	}



	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
