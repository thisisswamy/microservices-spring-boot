package com.swamy.microservice.basics.services;

import java.util.List;

import com.swamy.microservice.basics.models.UserRequest;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.models.UserWithReviews;

public interface UserCommonService {

	String registerUser(UserRequest userRequest);

	List<UserResponse> getAllUsersList();

	UserWithReviews getUserWithReviews(String userName);

	boolean resetPassword(UserRequest userRequest);


}
