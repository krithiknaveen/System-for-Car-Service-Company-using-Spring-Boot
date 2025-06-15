package com.cts.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cts.dtos.LoginDto;
import com.cts.security.JwtTokenProvider;
import com.cts.service.AuthService;

/*
 * Implementation of the AuthService interface for handling user authentication.
 */

@Service
public class AuthServiceImplementation implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/*
     * Authenticates the user using the provided login credentials and returns a JWT token.
	 * It returns a JWT token if authentication is successful
     */
	@Override
	public String login(LoginDto loginDto) {

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return token;

	}
}
