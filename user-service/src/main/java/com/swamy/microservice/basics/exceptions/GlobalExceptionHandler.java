package com.swamy.microservice.basics.exceptions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UrlPathHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.InternalServerErrorException;

//@RestControllerAdvice
public class GlobalExceptionHandler{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@ExceptionHandler(ResponseStatusException.class)
	public void noDataFoundException(HttpServletRequest request,	HttpServletResponse response) throws JsonProcessingException, IOException {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		Map<String, Object> data = new HashMap<>();
		String path = new UrlPathHelper().getPathWithinApplication(request);
		LocalDateTime current = LocalDateTime.now();
		data.put("timestamp", current.toString());
		data.put("exception", "No Data Found");
		data.put("url", path);
		response.getOutputStream().println(objectMapper.writeValueAsString(data));

	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public void internalServerExpetionOnSavingUser(HttpServletRequest request,	HttpServletResponse response) throws JsonProcessingException, IOException {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		Map<String, Object> data = new HashMap<>();
		String path = new UrlPathHelper().getPathWithinApplication(request);
		LocalDateTime current = LocalDateTime.now();
		data.put("timestamp", current.toString());
		data.put("exception", "Inernal Server Error");
		data.put("url", path);
		response.getOutputStream().println(objectMapper.writeValueAsString(data));

	}
}
