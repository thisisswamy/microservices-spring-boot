package com.swamy.microservice2.basics.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.docs.ReviewFormImage;
import com.swamy.microservice2.basics.models.CommonResponseModel;
import com.swamy.microservice2.basics.models.ErrorMessage;
import com.swamy.microservice2.basics.models.ReviewResponse;
import com.swamy.microservice2.basics.models.UserInfo;
import com.swamy.microservice2.basics.repos.ReviewRepo;
import com.swamy.microservice2.basics.utities.ReviewUtility;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final static String POSTERS_FOLDER = "C:/Users/SWAMY/OneDrive/Desktop/Full-Stack-Applications/Angular/movie-review-app/src/assets/posters/";

	@Autowired
	private ReviewRepo repo;

	@Autowired
	private ReviewUtility utility;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommonResponseModel writeReview(ReviewResponse req) {
		boolean isWritten = utility.getReviewByMovieName(req.getMovieName());
		if (isWritten) {
			return new CommonResponseModel("ALready written please to edit or write for another moview");
		} else {
			Review review = new Review();
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
	public List<ReviewResponse> getAllReviewsOfUser(UserInfo userInfo) throws IOException {
		return repo.findByUserName(userInfo.getUserName())
		.stream().map(review ->  modelMapper.map(review, ReviewResponse.class))
		.collect(Collectors.toList());

	}

	@Override
	public List<ReviewResponse> getAllReviews() {

		return repo.findAll().stream().map(reviewListObj -> modelMapper.map(reviewListObj, ReviewResponse.class))
				.collect(Collectors.toList());

	}

	@Override
	public CommonResponseModel deleteReviewByKey(String key) {
		Review isPresent = utility.getReviewObject(key);
		if (isPresent !=null) {
			repo.deleteByKey(key);
			File file = new File(POSTERS_FOLDER  + isPresent.getReviewFormImage().getImageName());
			file.delete();
			return new CommonResponseModel("successfully deleted");
		} else {
			return new CommonResponseModel("Review is Not present please enter correct key");
		}
	}

	@Override
	public ErrorMessage updateReview(ReviewResponse res) {
		Review isWritten = utility.getReviewObject(res.getKey());
		if (isWritten != null) {
			isWritten.setMovieName(res.getMovieName());
			isWritten.setCastCrew(res.getCastCrew());
			isWritten.setRating(res.getRating());
			isWritten.setUserName(res.getUserName());
			isWritten.setVerdict(res.getVerdict());
			isWritten.setLanguage(res.getLanguage());
			isWritten.setKey(res.getKey());


			repo.save(isWritten);
			return new ErrorMessage("Review Updated Successfully", 200);
		}

		return new ErrorMessage("Please provide valide data", 404);
	}

	@Override
	public CommonResponseModel writeReviewWithPoster(ReviewResponse reviewResponse, MultipartFile multipartFile)
			throws IOException {
		
		String filePath = POSTERS_FOLDER + StringUtils.cleanPath(multipartFile.getOriginalFilename());

		boolean isWritten = utility.getReviewByMovieName(reviewResponse.getMovieName());
		if (isWritten) {
			return new CommonResponseModel("ALready written please to edit or write for another moview");
		} else {

			// poster
			ReviewFormImage img = new ReviewFormImage(StringUtils.cleanPath(multipartFile.getOriginalFilename()),
					multipartFile.getSize(), multipartFile.getContentType());

			Review review = new Review(reviewResponse.getUserName(), reviewResponse.getMovieName(),
					reviewResponse.getRating(), reviewResponse.getVerdict(), reviewResponse.getCastCrew(),
					reviewResponse.getKey(), reviewResponse.getLanguage(), img);
			repo.save(review);
			
			multipartFile.transferTo(new File(filePath));
			return new CommonResponseModel("Successfully Saved Review");
		}
	}

}
