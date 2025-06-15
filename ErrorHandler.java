package com.cts.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cts.dtos.ErrorResponse;
import com.cts.exceptions.CarServiceNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class ErrorHandler {
	

	/*
     * Handles CarServiceNotFoundException when a requested car service is not found.
     * Returns a NOT_FOUND (404) status with an appropriate error message.
     */
	@ExceptionHandler(CarServiceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCarServiceNotFound(CarServiceNotFoundException ex) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	

	/*
     * Handles validation errors for method arguments annotated with @Valid.
     * Aggregates all field errors and returns a BAD_REQUEST (400) status.
     */


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		StringBuilder sb = new StringBuilder();
		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			sb.append(f.getField() + ": " + f.getDefaultMessage() +" ");
			

		}
		ErrorResponse resp = new ErrorResponse();
		resp.setMessage(sb.toString());
		return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
	}
	

	/*
     * Handles cases where the request body is not readable or has invalid format.
     * Typically occurs when JSON is malformed or fields have incorrect types.
     */
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormat(HttpMessageNotReadableException ex) {
		
		ErrorResponse response = new ErrorResponse();
		response.setMessage("Invalid input format. Please check field types.");
		
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
    }
	

	/*
     * Handles HTTP method not supported exceptions.
     * Returns a METHOD_NOT_ALLOWED (405) status with the exception message.
     */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleInvalidMethod(HttpRequestMethodNotSupportedException ex){
		
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.METHOD_NOT_ALLOWED);
	}
	

	/*
     * Handles SQL integrity constraint violations, such as duplicate keys or foreign key issues.
     * Returns a BAD_REQUEST (400) status with the exception message.
     */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleSqlExceptions(SQLIntegrityConstraintViolationException ex){
		
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
		
	}
	

	/*
     * Handles method argument type mismatches, such as passing a string instead of an integer.
     * Returns a METHOD_NOT_ALLOWED (405) status with the exception message.
     */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArguments(MethodArgumentTypeMismatchException ex){
		

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	// Handles validation errors for ConstraintViolationException (HTTP 400 Bad Request)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String errorMessage = violations.stream()
            .map(v -> v.getPropertyPath() + ": " + v.getMessage())
            .collect(Collectors.joining(" "));
 
        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(errorMessage);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleMethodMediaType(HttpMediaTypeNotSupportedException ex){
		

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.METHOD_NOT_ALLOWED);
		
	}
}
	