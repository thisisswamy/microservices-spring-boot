package com.swamy.microservice2.basics.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.swamy.microservice2.basics.models.UserResponse;



@FeignClient(name = "user-ms-service",path = "/api/v1/user")
public interface UserAuthFeinClient {
	
	@GetMapping("/validateUser")
	public ResponseEntity<UserResponse> validateUserCreds();
	
	@GetMapping("/UserDetails/token")
	ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token);
}
