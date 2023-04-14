package com.swamy.microservice.basics.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swamy.microservice.basics.entity.User;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.repos.UserRepo;

@Component
public class UserUtilities {
	
	@Autowired
	private UserRepo userRepo;
	
	public boolean isUserPresent(String email) {
		User user = userRepo.findByEmailAddress(email);
		return  user != null ? true : false;
	}
	
	public UserResponse getUserByMailID(String email) {
		User user = userRepo.findByEmailAddress(email);
		if(user!=null) {
			return  new UserResponse(user.getUserName(),user.getEmailAddress(),user.getDisplayName());
		}
		return null;	
	}
	
	

}
