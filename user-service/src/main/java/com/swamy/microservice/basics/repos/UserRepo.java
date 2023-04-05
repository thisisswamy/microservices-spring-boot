package com.swamy.microservice.basics.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swamy.microservice.basics.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmailAddress(String email);

	User findByUserName(String userName);

}
