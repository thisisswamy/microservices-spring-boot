package com.swamy.microservice2.basics.models;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
	private String userName;

	public UserInfo() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + "]";
	}

	public UserInfo(String userName) {
		super();
		this.userName = userName;
	}
	

}
