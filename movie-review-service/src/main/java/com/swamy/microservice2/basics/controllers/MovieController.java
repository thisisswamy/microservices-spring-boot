package com.swamy.microservice2.basics.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swamy.microservice2.basics.docs.ReviewFormImage;
import com.swamy.microservice2.basics.models.CommonResponseModel;
import com.swamy.microservice2.basics.models.ErrorMessage;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;
import com.swamy.microservice2.basics.service.ReviewService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class MovieController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/write")
	public CommonResponseModel writeReview(@RequestBody ReviewResponse response) {
		return reviewService.writeReview(response);	
	}
	
	@PostMapping(value = "/write/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CommonResponseModel writeReviewWithPoster(@RequestPart("reviewForm") ReviewResponse reviewResponse,
													@RequestPart("poster") MultipartFile multipartFile	) throws IOException {
	
		return reviewService.writeReviewWithPoster(reviewResponse,multipartFile);	
	}
	
	@PutMapping("/update")
	public ErrorMessage updateReview(@RequestBody ReviewResponse response) {
		return reviewService.updateReview(response);	
	}
	
	@PostMapping("/user")
	public List<ReviewResponse> getAllReviewsOfUser(@RequestBody UserInfo userInfo) throws IOException{
		return reviewService.getAllReviewsOfUser(userInfo);
		
	}
	

	@DeleteMapping("/delete")
	public String deleteAllReviews() {
		return reviewService.deleteAllReviews();
	}
	
	@DeleteMapping("/delete/{key}")
	public CommonResponseModel deleteReviewByKey(@PathVariable String key) {
		return reviewService.deleteReviewByKey(key);
	}
	
	@GetMapping("/all")
	public List<ReviewResponse> getAllReviews(){
		return reviewService.getAllReviews();
	}

}
