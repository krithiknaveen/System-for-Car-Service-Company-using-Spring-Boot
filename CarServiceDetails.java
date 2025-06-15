package com.cts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cardetails")


/*
 * Entity class representing detailed information about a car service record.
 *
 * This class is mapped to the "cardetails" table in the database and is used to
 * persist data related to car servicing
 */

public class CarServiceDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Car Make cannot be empty.")
	@Pattern(regexp = "[A-Za-z\s]+",message = "Car Make should only contain alphabets")
	private String carMake;
	
	@NotBlank(message = "Model Name cannot be empty.")
	@Pattern(regexp = "^[A-Za-z0-9]+(?: [A-Za-z0-9]+)*$",message = "Model Name can only contain letters, numbers, and spaces. Special characters are not allowed.")
	private String modelName;
	
	@NotBlank(message = "Car Registration Number cannot be empty.")
	@Pattern(regexp = "^[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}$",message = "Enter a Valid Registration Number")
	@Column(unique = true)
	private String carRegistrationNumber;
	
	@NotBlank(message = "Known Issues cannot be empty.")
	@Pattern(regexp = "^[A-Za-z0-9,:;.\\s]+$",message ="Invalid Entry, No special characters should be entered")
	private String knownIssues;
	
	@NotBlank(message = "Car Chassis Number cannot be empty.")
	@Pattern(regexp = "^[A-HJ-NPR-Z0-9]+{17}$",message = "Invalid Entry, Enter a Valid Chassis Number")
	@Column(unique = true)
	private String carChassisNumber; 
	
	@Digits(integer = 8,message = "Cost should be more than 9999999.99 and decimal value should not exceed 0.99(2 digits after decimal)",fraction = 2)
	@Min(value = 1,message = "Cost must be at least 1.00")
	@NotNull(message = "Cost should not be Null")
	private BigDecimal cost;
	
	@NotNull(message = "Given Date Cannot be empty")
	@PastOrPresent(message = "Given date should be past or present date. Enter a valid date")
	private LocalDate givenDate;
	
	@NotNull(message = "Expected Delivery Date Cannot be empty")
	@FutureOrPresent(message = "Expected delivery date must be Future. Enter a valid date")
	private LocalDate expectedDeliveryDate;
	
	@NotNull(message = "Created date and time cannot be null")
	private LocalDateTime createdDateTime;
	
	@NotNull(message = "Updated date and time cannot be null")
	private LocalDateTime updatedDateTime;
	
	@NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits long")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid Indian mobile number format. Must be 10 digits and start with 6, 7, 8, or 9.")
	private String phoneNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName = "id")
	private Address address;
}