package com.cts.service;

import java.util.List;

import com.cts.dtos.CarServiceDetailsDto;
import com.cts.dtos.CarServiceDetailsUpdateDto;

/*
 * Service interface for managing car service details.
 */

public interface CarServiceDetailsService {

	/*
     * Retrieves a list of all car service records.
     */
	List<CarServiceDetailsDto> getAllService();

	/*
     * Retrieves a specific car service record by its ID.
     */
	CarServiceDetailsDto getById(int id);

	/*
     * Adds a new car service record.
     */
	CarServiceDetailsDto addCarService(CarServiceDetailsDto carServiceDetails);

	/*
     * Updates an existing car service record by its ID.
     */
	CarServiceDetailsDto updateById(int id, CarServiceDetailsUpdateDto carServiceDetails);

	/*
     * Deletes a car service record by its ID.
     */
	void deleteById(int id);
}