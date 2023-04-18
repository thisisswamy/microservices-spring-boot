package com.swamy.microservice.basics.auth;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.microservice.basics.JWT.JwtAuthRequest;
import com.swamy.microservice.basics.JWT.JwtToken;
import com.swamy.microservice.basics.JWT.JwtUtil;
import com.swamy.microservice.basics.configuration.CustomUserDetailsService;
import com.swamy.microservice.basics.entity.User;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.repos.UserRepo;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserResponse userResponse;

	@PostMapping("/auth/login")
	public ResponseEntity<?> loginToken(@RequestBody JwtAuthRequest auth) throws Exception {

		try {

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					auth.getEmail(), auth.getPassword(), new ArrayList());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new Exception("invalid username/Email..");
		}
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(auth.getEmail());
		String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtToken(jwt));
	}

	@GetMapping("/validateUser")
	public ResponseEntity<UserResponse> validateUserCreds(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmailAddress(authentication.getPrincipal().toString());
		UserResponse userResponse = new UserResponse(user.getUserName(),user.getEmailAddress(),user.getDisplayName());
		return ResponseEntity.ok(userResponse);
	}
	
	@GetMapping("/UserDetails/token")
	ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmailAddress(authentication.getPrincipal().toString());
		UserResponse userResponse = new UserResponse(user.getUserName(),user.getEmailAddress(),user.getDisplayName());
		return ResponseEntity.ok(userResponse);
	}
	
	@GetMapping("/user-verify/token")
	boolean isUserAuthencated(@RequestHeader("Authorization") String token) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmailAddress(authentication.getPrincipal().toString());
		return  user != null ? true : false;
	}
	
}
