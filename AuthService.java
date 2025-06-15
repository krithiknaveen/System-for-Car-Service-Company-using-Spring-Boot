package com.cts.service;

import com.cts.dtos.LoginDto;

/*
 * Service interface for handling authentication-related operations.
 */

public interface AuthService {
	/*
     *This method is used to Authenticates a user based on the provided login credentials.
     */
	
	String login(LoginDto loginDto);

}
