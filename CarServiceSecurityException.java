package com.cts.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

/*
 * Custom exception class for handling security-related errors in the car service application.
 */

public class CarServiceSecurityException extends RuntimeException{

	private HttpStatus status;
	private String message;
	
	
}
