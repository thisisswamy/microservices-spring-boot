package com.swamy.microservice2.basics.service;



import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.swamy.microservice2.basics.docs.ReviewFormImage;
import com.swamy.microservice2.basics.models.CommonResponseModel;
import com.swamy.microservice2.basics.models.ErrorMessage;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;


public interface ReviewService {

	CommonResponseModel writeReview(ReviewResponse response);

	String deleteAllReviews();

	List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo) throws IOException;

	List<ReviewResponse> getAllReviews();

	CommonResponseModel deleteReviewByKey(String key);

	ErrorMessage updateReview(ReviewResponse response);

	CommonResponseModel writeReviewWithPoster(ReviewResponse reviewResponse, MultipartFile multipartFile) throws IOException;

	

}
