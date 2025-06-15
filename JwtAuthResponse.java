package com.cts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

/*
 * Data Transfer Object (DTO) for sending JWT authentication responses.
 */

public class JwtAuthResponse {
	
	private String jwtToken;
	private String type = "Bearer";

}
