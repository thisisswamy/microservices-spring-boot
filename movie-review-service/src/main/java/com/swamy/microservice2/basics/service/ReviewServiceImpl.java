package com.swamy.microservice2.basics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.models.CommonResponseModel;
import com.swamy.microservice2.basics.models.ErrorMessage;
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
	public CommonResponseModel writeReview(ReviewResponse req) {
		boolean isWritten = utility.getReviewByMovieName(req.getMovieName());
		if(isWritten) {
			return new CommonResponseModel("ALready written please to edit or write for another moview");
		}else {
			Review review=new Review();
			review.setMovieName(req.getMovieName());
			review.setRating(req.getRating());
			review.setVerdict(req.getVerdict());
			review.setUserName(req.getUserName());
			review.setCastCrew(req.getCastCrew());
			review.setLanguage(req.getLanguage());
			review.setKey(req.getKey());
			repo.save(review);
			return new CommonResponseModel("Successfully Saved Review");
		}
		
	}

	@Override
	public String deleteAllReviews() {
		repo.deleteAll();
		return "deleted SuccessFully ";
	}

	@Override
	public List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo) {
		List<Review> findByUserName = repo.findByUserName(userInfo.getUserName());
		List<ReviewResponse> list = new ArrayList<>();
		findByUserName.stream().forEach(t->{
			list.add(modelMapper.map(t, ReviewResponse.class));
		});
		return list;
		
	}

	

	@Override
	public List<ReviewResponse> getAllReviews() {
		List<Review> reviewList = repo.findAll();
		List<ReviewResponse> list=new ArrayList<>();
		reviewList.stream().forEach(t->{
			ReviewResponse map = modelMapper.map(t, ReviewResponse.class);
			list.add(map);
		});
		return list;
	}

	@Override
	public CommonResponseModel deleteReviewByKey(String key) {
		boolean isPresent = utility.isReviewWritten(key);
		if(isPresent) {
			repo.deleteByKey(key);
			return new CommonResponseModel("successfully deleted");
		}else {
			return new CommonResponseModel("Review is Not present please enter correct key");
		}
	}

	@Override
	public ErrorMessage updateReview(ReviewResponse res) {
		Review isWritten = utility.getReviewObject(res.getKey());
		if(isWritten!=null) {
			isWritten.setMovieName(res.getMovieName());
			isWritten.setCastCrew(res.getCastCrew());
			isWritten.setRating(res.getRating());
			isWritten.setUserName(res.getUserName());
			isWritten.setVerdict(res.getVerdict());
			isWritten.setLanguage(res.getLanguage());
			isWritten.setKey(res.getKey());		
			
//			Review map = modelMapper.map(res,Review.class);
			repo.save(isWritten);
			return new ErrorMessage("Review Updated Successfully",200);
		}
		
		return new ErrorMessage("Please provide valide data",404);
	}

	

}
