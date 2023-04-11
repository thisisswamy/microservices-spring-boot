package com.swamy.microservice2.basics.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.models.ReviewResponse;

public interface ReviewRepo extends MongoRepository<Review, String> {

	@Query(value = "{key : ?0}")
	Review findByKey(String key);
	
	@Query(value = "{ userName : ?0 }")
	public List<Review> findByUserName(String userName);
	
	@Query(value = "{ key : ?0 }",delete=true)
	void deleteByKey(String key);

	@Query(value = "{ movieName : ?0 }")
	Review findyMovieName(String movieName);
	
//	@Query(value = "{}",delete = true)
//	public String deleteAllDocuments();

}
