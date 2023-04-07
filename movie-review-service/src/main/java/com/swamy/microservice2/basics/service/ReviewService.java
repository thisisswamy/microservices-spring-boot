package com.swamy.microservice2.basics.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.swamy.microservice2.basics.models.ErrorMessage;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;


public interface ReviewService {

	String writeReview(ReviewResponse response);

	String deleteAllReviews();

	List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo);

	List<ReviewResponse> getAllReviewsByUserName(String userName);

	List<ReviewResponse> getAllReviews();

	String deleteReviewByKey(String key);

	ErrorMessage updateReview(ReviewResponse response);

}
