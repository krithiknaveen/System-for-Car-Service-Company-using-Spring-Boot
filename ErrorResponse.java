package com.cts.dtos;


/*
 * Data Transfer Object (DTO) for representing error responses in the application.
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
	private String message;
	
}
