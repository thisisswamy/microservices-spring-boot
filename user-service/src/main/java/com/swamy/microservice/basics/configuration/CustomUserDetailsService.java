package com.swamy.microservice.basics.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.swamy.microservice.basics.repos.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.swamy.microservice.basics.entity.User user = userRepo.findByEmailAddress(username);
		String email =user.getEmailAddress();
		String password =user.getPassword();
		List<GrantedAuthority> roles =List.of(new SimpleGrantedAuthority("USER"));
		return new User(email,password,roles);
	}

}
