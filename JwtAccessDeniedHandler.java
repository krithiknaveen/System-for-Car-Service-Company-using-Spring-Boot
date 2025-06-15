package com.cts.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Custom handler for managing access denied exceptions in a Spring Security context.
 */

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	

	/*
     * Handles AccessDeniedException by sending a custom JSON response.
     */
	
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        System.out.println("Handling AccessDeniedException");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Access denied\"}");
        response.getWriter().flush();
    }
}

