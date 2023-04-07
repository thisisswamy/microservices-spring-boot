package com.swamy.microservice2.basics.models;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	
	private String message;
	private long code;
	public ErrorMessage(String message, long code) {
		super();
		this.message = message;
		this.code = code;
	}
	public ErrorMessage() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	

}
