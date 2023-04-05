package com.swamy.microservice2.basics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;
import com.swamy.microservice2.basics.repos.ReviewRepo;
import com.swamy.microservice2.basics.utities.ReviewUtility;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepo repo;
	
	@Autowired
	private ReviewUtility utility;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String writeReview(ReviewResponse req) {
		boolean isWritten = utility.isReviewWritten(req.getKey());
		if(isWritten) {
			return "ALready written please to edit or write for another moview";
		}else {
			Review review=new Review();
			review.setMovieName(req.getMovieName());
			review.setRating(req.getRating());
			review.setVerdict(req.getVerdict());
			review.setUserName(req.getUserName());
			review.setCastCrew(req.getCastCrew());
			review.setKey(req.getKey());
			repo.save(review);
			System.out.println(review);
			return "Review saved successfully";
		}
		
	}

	@Override
	public String deleteAllReviews() {
		repo.deleteAll();
		return "deleted SuccessFully ";
	}

	@Override
	public List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo) {
		List<ReviewResponse> findByUserNameList = repo.findByUserName(userInfo.getUserName());
		return findByUserNameList;
		
	}

	@Override
	public List<ReviewResponse> getAllReviewsByUserName(String userName) {
		List<ReviewResponse> reviews = repo.findByUserName(userName);
		return reviews;
	}

}
