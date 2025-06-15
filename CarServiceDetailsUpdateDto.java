package com.cts.dtos;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.TransactionSystemException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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


/*
 * Data Transfer Object (DTO) for capturing and transferring car service details for Update by ID operation.
 */

public class CarServiceDetailsUpdateDto {
	
	private int id;
	
	@Pattern(regexp = "[A-Za-z\s]+",message = "Car Make should only contain alphabets")
	private String carMake;
	
	
	@Pattern(regexp = "^[A-Za-z0-9]+(?: [A-Za-z0-9]+)*$",message = "Model Name can only contain letters, numbers, and spaces. Special characters are not allowed.")
	private String modelName;
	
	@Pattern(regexp = "^[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}$",message = "Enter a Valid Registration Number")
	private String carRegistrationNumber;
	
	@Pattern(regexp = "^[A-Za-z0-9,:;.\\s]+$",message ="Invalid Entry, No special characters should be entered")
	private String knownIssues;
	
	@Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$",message = "Invalid Entry, Enter a Valid Chassis Number")
	private String carChassisNumber;
	
	@Digits(integer = 8,message = "Cost should be more than 9999999.99 and decimal value should not exceed 0.99(2 digits after decimal)",fraction = 2)
	@Min(value = 1,message = "Cost must be at least 1.00")
	private BigDecimal cost;
	
	
	@PastOrPresent(message = "Given date should be past or present date. Enter a valid date")
	private LocalDate givenDate;
	
	
	@FutureOrPresent(message = "Expected delivery date must be Future. Enter a valid date")
	private LocalDate expectedDeliveryDate;
	
	@CreatedDate
	private LocalDateTime createdDateTime;
	
	
	private LocalDateTime updatedDateTime;
	
	
	
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits long")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid Indian mobile number format. Must be 10 digits and start with 6, 7, 8, or 9.")
	private String phoneNumber;
	
	@Valid
	private AddressUpdateDto address;
	
	TransactionSystemException exception = new TransactionSystemException(carChassisNumber);
	ConstraintViolationException constraintViolationException = new ConstraintViolationException(null);
}