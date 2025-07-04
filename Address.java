package com.cts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


/*
 * Entity class representing an address in the system.
 *
 * This class is mapped to the "address" table in the database and is used to
 * persist address-related information
 */

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "House number cannot be empty.")
	@Size(max = 10, message = "House number should not exceed 10 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\-\\/,]{1,10}$", message = "House number can only contain letters, numbers, hyphens, and slashes.")
	private String houseNumber;
	
	@NotBlank(message = "Street name cannot be empty.")
	@Size(max = 50, message = "Street name should not exceed 50 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\s]{1,50}$", message = "Street name can only contain letters, numbers, and spaces.")
	private String street;
	
	@Size(max = 50, message = "Landmark should not exceed 50 characters.")
	@Pattern(regexp = "^[A-Za-z0-9\\s,\\.]{1,50}$", message = "Landmark can contain letters, numbers, spaces, and commas.")
	private String landmark;
	
	@NotBlank(message = "City name cannot be empty.")
	@Size(max = 30, message = "City name should not exceed 30 characters.")
	@Pattern(regexp = "^[A-Za-z\\s]{1,30}$", message = "City name must contain only alphabets and spaces.")
	private String city;
	
	@NotBlank(message = "State name cannot be empty.")
	@Size(max = 30, message = "State name should not exceed 30 characters.")
	@Pattern(regexp = "^[A-Za-z\\s]{1,30}$", message = "State name must contain only alphabets and spaces.")
	private String state;
	
	@NotBlank(message = "Pincode cannot be empty.")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid pincode format. Pincode must be a 6-digit number and cannot start with 0.")
	private String pincode;

}


