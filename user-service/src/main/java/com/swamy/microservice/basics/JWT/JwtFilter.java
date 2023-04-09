package com.swamy.microservice.basics.JWT;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.swamy.microservice.basics.configuration.CustomUserDetailsService;
import com.swamy.microservice.basics.entity.User;
import com.swamy.microservice.basics.models.UserResponse;
import com.swamy.microservice.basics.repos.UserRepo;


@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
    private CustomUserDetailsService customUserService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepo userRepo;

   
	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
        System.err.println("user ms filter token>>> "+authorizationHeader);
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customUserService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                		new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                
            }
  
        }
        filterChain.doFilter(request, response);
		
	}


}
