package com.swamy.microservice2.basics.docs;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.servlet.annotation.MultipartConfig;

@Document(value = "reviews")
public class Review {
	public Review(String userName, String movieName, String rating, String verdict, List<String> castCrew,
			String key, String language, ReviewFormImage reviewFormImage) {
		super();
		this.id = id;
		this.userName = userName;
		this.movieName = movieName;
		this.rating = rating;
		this.verdict = verdict;
		this.castCrew = castCrew;
		this.key = key;
		this.language = language;
		this.reviewFormImage = reviewFormImage;
	}

	@Id
	private String id;
	private String userName;
	private String movieName;
	private String rating;
	private String verdict;
	private List<String> castCrew;
	private String key;
	private String language;
	private ReviewFormImage reviewFormImage;
	
	
	public ReviewFormImage getReviewFormImage() {
		return reviewFormImage;
	}
	public void setReviewFormImage(ReviewFormImage reviewFormImage) {
		this.reviewFormImage = reviewFormImage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public Review() {
		super();
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", userName=" + userName + ", movieName=" + movieName + ", rating=" + rating
				+ ", verdict=" + verdict + ", castCrew=" + castCrew + ", key=" + key + "]";
	}
	

}
