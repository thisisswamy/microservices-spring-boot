package com.swamy.microservice2.basics.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ReviewResponse {

	
	private String id;
	private String userName;
	private String movieName;
	private String rating;
	private String verdict;
	private List<String> castCrew;
	private String key;
	private String language;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public ReviewResponse() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getVerdict() {
		return verdict;
	}
	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
	public List<String> getCastCrew() {
		return castCrew;
	}
	public void setCastCrew(List<String> castCrew) {
		this.castCrew = castCrew;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	

}
