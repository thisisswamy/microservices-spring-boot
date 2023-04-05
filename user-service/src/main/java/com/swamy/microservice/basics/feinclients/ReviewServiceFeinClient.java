package com.swamy.microservice.basics.feinclients;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.swamy.microservice.basics.models.ReviewResponse;


@FeignClient(name = "MOVIEW-REVIEW-MS",path="/api/v1/reviews")
public interface ReviewServiceFeinClient {
	
	@GetMapping("/user/{userName}")
	public List<ReviewResponse> getAllReviewsByUserName(@PathVariable String userName);
}
