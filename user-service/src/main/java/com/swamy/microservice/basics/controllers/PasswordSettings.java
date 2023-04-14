package com.swamy.microservice.basics.controllers;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swamy.microservice.basics.models.ForgotPassword;
import com.swamy.microservice.basics.models.UserRequest;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.repos.UserRepo;
import com.swamy.microservice.basics.services.UserCommonService;
import com.swamy.microservice.basics.utilities.UserUtilities;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PasswordSettings {
	
	
	@Autowired
	private UserCommonService commonService;
	
	@Autowired
	private UserUtilities userUtilities;
	@PostMapping("/password/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword){

		UserResponse userByMailID = userUtilities.getUserByMailID(forgotPassword.getEmail());
		if(userByMailID!=null) {
			return ResponseEntity.ok(userByMailID);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
	}
	
	@PostMapping("password/reset")
	public ResponseEntity<?> resetPassword(@RequestBody UserRequest userRequest){
		boolean saved = commonService.resetPassword(userRequest);
		if(saved) {
			return ResponseEntity.ok("Successfully changed Password.");
		}else {
			throw new InternalServerErrorException();
		}
	}

	

}
