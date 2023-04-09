package com.swamy.microservice.basics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.microservice.basics.JWT.JwtUtil;
import com.swamy.microservice.basics.configuration.CustomUserDetailsService;
import com.swamy.microservice.basics.models.CustomHttpResponse;
import com.swamy.microservice.basics.models.UserRequest;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.models.UserWithReviews;
import com.swamy.microservice.basics.services.UserCommonService;




@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserAccountController {
	
	@Autowired
	private UserCommonService commonService;
	
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public CustomHttpResponse registerUser(@RequestBody UserRequest userRequest) {
		return commonService.registerUser(userRequest);
	}
	
	@GetMapping("/all")
	public List<UserResponse> getUsersList(){
		return commonService.getAllUsersList();
	}
	
	@GetMapping("/{userName}")
	public UserWithReviews getUserWithReviews(@PathVariable String userName) {
		System.err.println(userName);
		return commonService.getUserWithReviews(userName);
	}
	

	

}
