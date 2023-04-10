package com.swamy.microservice.basics.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.swamy.microservice.basics.JWT.JwtFilter;
import com.swamy.microservice.basics.exceptions.JwtEntryPointException;

import com.swamy.microservice.basics.repos.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@EnableWebSecurity
@Configuration
public class MySecurityConfig {

	private static final String[] PUBLIC_URLS = {"/register", "/auth/login","/password/forgot-password","password/reset"};
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Autowired
	private JwtEntryPointException jwtEntryPointException;

	
	
	@Bean  //Exposing some routes to publicly
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PUBLIC_URLS);
    }
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable().cors().and()
	        
	        .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
	        
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .exceptionHandling().authenticationEntryPoint(jwtEntryPointException);
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);        
	        return http.build();
	 }
	 
	 
	 
	 @Bean
	 public AuthenticationProvider manager() {
		 DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		 authProvider.setUserDetailsService(customUserDetailsService);
		 authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		 return authProvider;
	 }
	 
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception { 
		 return configuration.getAuthenticationManager();
	 }
	 
//	 @Bean
//	 CorsConfigurationSource corsConfigurationSource() {
//	 	CorsConfiguration configuration = new CorsConfiguration();
//	 	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//	 	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
//	 	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	 	source.registerCorsConfiguration("/**", configuration);
//	 	return source;
//	 }
	
	 
	 

	 
	
}
