package com.swamy.microservice2.basics.service;



import java.util.List;

import com.swamy.microservice2.basics.models.CommonResponseModel;
import com.swamy.microservice2.basics.models.ErrorMessage;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;


public interface ReviewService {

	CommonResponseModel writeReview(ReviewResponse response);

	String deleteAllReviews();

	List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo);

	List<ReviewResponse> getAllReviews();

	CommonResponseModel deleteReviewByKey(String key);

	ErrorMessage updateReview(ReviewResponse response);

	

}
