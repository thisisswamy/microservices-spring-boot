package com.swamy.microservice2.basics.utities;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.repos.ReviewRepo;

@Component
public class ReviewUtility {
	
	@Autowired
	private ReviewRepo repo;
	
	public boolean isReviewWritten(String key) {
		Review review=repo.findByKey(key);
		return review != null ? true : false;
	}
	
	public Review getReviewObject(String key) {
		Review review=repo.findByKey(key);
		return review;
	}
	
	public boolean getReviewByMovieName(String movieName) {
		List<Review> findAll = repo.findAll();
		boolean anyMatch = findAll.stream().anyMatch(t->t.getMovieName().toLowerCase().equals(movieName.toLowerCase()));
		return anyMatch;
	}

	

}
