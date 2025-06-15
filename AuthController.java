package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dtos.JwtAuthResponse;
import com.cts.dtos.LoginDto;
import com.cts.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	/*
	 * Authenticates a user based on the provided login credentials.      
	 * If authentication is successful, returns a JWT token in the response     
	   with HTTP 200 OK.     
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		String result = authService.login(loginDto);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setJwtToken(result);

		return ResponseEntity.ok(response);

	}

}
