package com.cts.dtos;
 

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@NoArgsConstructor
@Data


/*
 * Data Transfer Object (DTO) representing an address for Update by ID Operation
 */

public class AddressUpdateDto {
	
	private int id;
	
	@Size(max = 10, message = "House number should not exceed 10 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\-\\/]{1,10}$", message = "House number can only contain letters, numbers, hyphens, and slashes.")
	private String houseNumber;
	
	@Size(max = 50, message = "Street name should not exceed 50 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\s]{1,50}$", message = "Street name can only contain letters, numbers, and spaces.")
	private String street;
	
	@Size(max = 50, message = "Landmark should not exceed 50 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\s,\\.]{1,50}$", message = "Landmark can contain letters, numbers, spaces, and commas.")
	private String landmark;
	
	@Size(max = 30, message = "City name should not exceed 30 characters.")
	@Pattern(regexp = "^[A-Za-z\\s]{1,30}$", message = "City name must contain only alphabets and spaces.")
	private String city;
	
	@Size(max = 30, message = "State name should not exceed 30 characters.")
	@Pattern(regexp = "^[A-Za-z\\s]{1,30}$", message = "State name must contain only alphabets and spaces.")
	private String state;
	
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid pincode format. Pincode must be a 6-digit number and cannot start with 0.")
	private String pincode;
 
}