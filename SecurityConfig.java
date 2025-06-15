package com.cts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.security.JwtAccessDeniedHandler;
import com.cts.security.JwtAuthenticationEntryPoint;
import com.cts.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JwtAccessDeniedHandler handler;
	

	/*
     * Configures the security filter chain for the application.
     * Return the configured SecurityFilterChain
     * throws Exception in case of configuration errors
     */
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		http.csrf(config->config.disable());
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/api/auth/**","/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated())
		.exceptionHandling(ex->ex.accessDeniedHandler(handler).authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	

	/*
     * Provides a password encoder bean using BCrypt hashing algorithm.
     * It return a BCryptPasswordEncoder instance
     */
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	

	/*
     * Provides an authentication manager bean using the provided authentication configuration.
     * Return the authentication manager
     * throws Exception in case of configuration errors
     */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}

}
