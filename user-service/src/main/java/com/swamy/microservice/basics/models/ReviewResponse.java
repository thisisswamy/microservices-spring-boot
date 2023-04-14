package com.swamy.microservice.basics.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ReviewResponse {

	

	private String id;
	private String userName;
	private String movieName;
	private double rating;
	private String verdict;
	private List<String> castCrew;
	private String key;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
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
	@Override
	public String toString() {
		return "ReviewResponse [id=" + id + ", userName=" + userName + ", movieName=" + movieName + ", rating=" + rating
				+ ", verdict=" + verdict + ", castCrew=" + castCrew + ", key=" + key + "]";
	}

}
