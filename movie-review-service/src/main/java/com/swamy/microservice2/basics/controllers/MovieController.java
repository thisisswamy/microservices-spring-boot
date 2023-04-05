package com.swamy.microservice2.basics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;
import com.swamy.microservice2.basics.service.ReviewService;


@RestController
public class MovieController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/write")
	public String writeReview(@RequestBody ReviewResponse response) {
		return reviewService.writeReview(response);	
	}
	
	@GetMapping("/user")
	public List<ReviewResponse> getAllReviewsOfUser(@RequestBody UserInfo userInfo){
		return reviewService.getAllReviewsOfUser(userInfo);
	}
	
	@GetMapping("/user/{userName}")
	public List<ReviewResponse> getAllReviewsByUserName(@PathVariable String userName){
		return reviewService.getAllReviewsByUserName(userName);
	}
	
	@DeleteMapping("/delete")
	public String deleteAllReviews() {
		return reviewService.deleteAllReviews();
	}

}
