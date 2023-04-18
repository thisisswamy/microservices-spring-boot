package com.swamy.microservice2.basics.auth;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swamy.microservice2.basics.models.UserResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/*")
@Order(2)
public class AuthFilter implements Filter {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private UserAuthFeinClient userAuthFeinClient;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, Object> data = new HashMap<>();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		final String authorizationHeader = httpRequest.getHeader("Authorization");
		System.err.println("movie-ms-filter auth 2 filter ..."+ authorizationHeader);
		if (authorizationHeader == null) {
			if(authorizationHeader == null && (httpRequest.getRequestURI().contains("api-docs") ||httpRequest.getRequestURI().contains("swagger")  )) {
				System.err.println(httpRequest.getRequestURI());
				chain.doFilter(httpRequest, httpResponse);

			}
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			LocalDateTime current = LocalDateTime.now();
			data.put("timestamp", current.toString());
			data.put("exception", "Missing Authorization value in header");
			httpResponse.getOutputStream().println(objectMapper.writeValueAsString(data));
			return;
		}
		else if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			boolean validateUserCreds = userAuthFeinClient.isUserAuthencated(authorizationHeader);
			if(!validateUserCreds) {
				System.out.println(" Movie-ms-filter user UNauthentication >> "+ validateUserCreds);
				LocalDateTime current = LocalDateTime.now();
				data.put("timestamp", current.toString());
				data.put("exception", "Invalid credentials");
				httpResponse.getOutputStream().println(objectMapper.writeValueAsString(data));
				return;
			}
			
			else {
				System.out.println(" Movie-ms-filter user authenticated >> "+ validateUserCreds);
				
				chain.doFilter(httpRequest, httpResponse);
			}
			
			
		}

	}

}
