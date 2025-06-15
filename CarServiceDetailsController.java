package com.cts.controller;
 
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cts.dtos.CarServiceDetailsDto;
import com.cts.dtos.CarServiceDetailsUpdateDto;
import com.cts.service.CarServiceDetailsService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/carservice")
public class CarServiceDetailsController {

	private CarServiceDetailsService carServiceDetailsService;
 
	public CarServiceDetailsController(CarServiceDetailsService carServiceDetailsService) {
		super();
		this.carServiceDetailsService = carServiceDetailsService;
	}
	

	/*
     * Retrieves a list of all car service records.
     * Return List of CarServiceDetailsDto wrapped in ResponseEntity with HTTP 200 OK.
     */	
	@GetMapping
	public ResponseEntity<List<CarServiceDetailsDto>> getAll(){
		return ResponseEntity.ok(carServiceDetailsService.getAllService());
	}
	
	/*
     * Retrieves a specific car service record by its ID.
     * Return CarServiceDetailsDto wrapped in ResponseEntity with HTTP 200 OK.
     */
	@GetMapping("/{id}")
	public ResponseEntity<CarServiceDetailsDto> getById(@PathVariable int id){
		return ResponseEntity.ok(carServiceDetailsService.getById(id));
	}
	
	
	/*
     * Adds a new car service record.
     * Only accessible by users with ADMIN role.
     * Return The created CarServiceDetailsDto with HTTP 201 CREATED.
     */
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CarServiceDetailsDto> addNewService(@Valid @RequestBody CarServiceDetailsDto carServiceDetails){
		return ResponseEntity.status(HttpStatus.CREATED).body(carServiceDetailsService.addCarService(carServiceDetails));
	}
	

	/*
     * Updates an existing car service record by ID.
     * Only accessible by users with ADMIN role.
     * Return The updated CarServiceDetailsDto with HTTP 201 CREATED.
     */
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CarServiceDetailsDto> updateService(@PathVariable int id,@Valid @RequestBody CarServiceDetailsUpdateDto carServiceDetails){
		return ResponseEntity.status(HttpStatus.CREATED).body(carServiceDetailsService.updateById(id, carServiceDetails));
	}
	

	/*
     * Deletes a car service record by ID.
     * Only accessible by users with ADMIN role.
     * Return HTTP 202 ACCEPTED if deletion is successful.
     */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		carServiceDetailsService.deleteById(id);
		return ResponseEntity.accepted().build();
	}
 
}