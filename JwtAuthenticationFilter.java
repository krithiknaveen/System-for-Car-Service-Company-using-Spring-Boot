package com.cts.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.exceptions.CarServiceSecurityException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * JWT Authentication Filter for processing and validating JWT tokens in incoming HTTP requests.
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService detailsService;
	
	/*
     * Extracts the JWT token from the Authorization header of the request.
     */

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			String token = bearerToken.substring(7);
			return token;
		}
		return null;
	}
	

	/*
     * Filters each incoming request to check for a valid JWT token.
     *
     * If a valid token is found, it sets the authentication in the security context.
     * If the token is invalid or a CarServiceSecurityException is thrown,
       it returns an appropriate error response.
     */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = getTokenFromRequest(request);

			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

				String username = jwtTokenProvider.getUsername(token);

				UserDetails userDetails = detailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
		}

		catch (CarServiceSecurityException e) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":" + e.getMessage() + "}");
			return;

		}
		filterChain.doFilter(request, response);

	}

}
