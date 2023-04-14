package com.swamy.microservice.basics.models;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserWithReviews {
	private UserResponse userResponse;
	
	private List<ReviewResponse> movieResponse;
	public UserWithReviews() {
		super();
	}
	public UserWithReviews(UserResponse userResponse, List<ReviewResponse> movieResponse) {
		super();
		this.userResponse = userResponse;
		this.movieResponse = movieResponse;
	}
	public UserResponse getUserResponse() {
		return userResponse;
	}
	public void setUserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}
	public List<ReviewResponse> getMovieResponse() {
		return movieResponse;
	}
	public void setMovieResponse(List<ReviewResponse> movieResponse) {
		this.movieResponse = movieResponse;
	}
	
	
	

}
