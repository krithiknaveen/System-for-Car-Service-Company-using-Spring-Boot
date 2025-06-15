package com.cts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


/*
 * Data Transfer Object (DTO) for handling user login requests
 */

public class LoginDto {
	
	private String usernameOrEmail;
	private String password;

}

