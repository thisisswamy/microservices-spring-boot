package com.swamy.microservice2.basics.utities;

import java.util.List;

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
		System.out.println("updated>>>>> "+review);
		return review != null ? true : false;
	}
	
	public Review getReviewObject(String key) {
		Review review=repo.findByKey(key);
		return review;
	}

}
