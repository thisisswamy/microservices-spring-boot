package com.swamy.microservice.basics.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swamy.microservice.basics.entity.User;
import com.swamy.microservice.basics.feinclients.ReviewServiceFeinClient;
import com.swamy.microservice.basics.models.CustomHttpResponse;
import com.swamy.microservice.basics.models.ReviewResponse;
import com.swamy.microservice.basics.models.UserRequest;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.models.UserWithReviews;
import com.swamy.microservice.basics.repos.UserRepo;
import com.swamy.microservice.basics.utilities.UserUtilities;

@Service
public class UserCommonServiceImpl implements UserCommonService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserUtilities utility;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ReviewServiceFeinClient feinClient;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public CustomHttpResponse registerUser(UserRequest request) {
		boolean existedUser = utility.isUserPresent(request.getEmailAddress());
		if(existedUser) {
			return new CustomHttpResponse("User already exists please use different email or login.");
		}else {
			User user= new User();
			user.setUserName(request.getUserName());
			user.setEmailAddress(request.getEmailAddress());
			user.setDisplayName(request.getDisplayName());
			user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
			user.setConfirmPassword(bCryptPasswordEncoder.encode(request.getConfirmPassword()));
			userRepo.save(user);
			return new CustomHttpResponse( "User registered successfully..");
		}
		
	}

	@Override
	public List<UserResponse> getAllUsersList() {
		List<User> usersList = userRepo.findAll();
		List<UserResponse> users =new ArrayList<>();
		usersList.stream().forEach(t->{
			users.add(new UserResponse(t.getUserName(), t.getEmailAddress(),t.getDisplayName()));
		});
		return users;
	}

	@Override
	public UserWithReviews getUserWithReviews(String userName) {
		//rest call to movie reviews
		List<ReviewResponse> allReviewsByUserName = feinClient.getAllReviewsByUserName(userName);
		//getting user from db
		User user = userRepo.findByUserName(userName);
		UserResponse res =new UserResponse();
		res.setUserName(user.getUserName());
		res.setEmailAddress(user.getEmailAddress());
		
		//setting user + review
		UserWithReviews userWithReview =new UserWithReviews();
		userWithReview.setMovieResponse(allReviewsByUserName);
		userWithReview.setUserResponse(res);
		
		return userWithReview;
	}

	@Override
	public boolean resetPassword(UserRequest userRequest) {
		User user = userRepo.findByEmailAddress(userRequest.getEmailAddress());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getConfirmPassword()));
		User updatedUser = userRepo.save(user);
		System.out.println(updatedUser);
		if(updatedUser!=null) {
			return true;
		}
		return false;
	}

	
	

}
