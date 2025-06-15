package com.cts.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Custom authentication entry point for handling unauthorized access attempts.
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	

	/*
     * Handles unauthorized access attempts by sending a custom JSON response.
	 *
	 * This method is triggered when a user tries to access a secured endpoint without
       proper authentication. It checks if the Authorization header is missing or empty,
       and returns a 400 Bad Request in that case. Otherwise, it returns a 401 Unauthorized
       status with the exception message

     */

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String authHeader = request.getHeader("Authorization");
		
		String path = request.getRequestURI();
		if(!(path.equals("/api/auth/login"))&&(authHeader == null || authHeader.isBlank())) {
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":" + "Authorization header (Token) is missing or empty" + "}");
			
		}
		else {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().write("{\"error\":" + authException.getMessage() + "}");

	}}
}
