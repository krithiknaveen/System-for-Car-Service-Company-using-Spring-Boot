package com.cts.exceptions;

/*
 * Custom exception class for handling cases where a car service record is not found.
 */

public class CarServiceNotFoundException extends RuntimeException {

	public CarServiceNotFoundException() {
		super();
	}

	public CarServiceNotFoundException(String message) {
		super(message);
		
	}
	

}
