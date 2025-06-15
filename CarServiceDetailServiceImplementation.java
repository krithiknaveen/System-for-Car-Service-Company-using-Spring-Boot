package com.cts.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.cts.dtos.AddressDto;
import com.cts.dtos.AddressUpdateDto;
import com.cts.dtos.CarServiceDetailsDto;
import com.cts.dtos.CarServiceDetailsUpdateDto;
import com.cts.entity.Address;
import com.cts.entity.CarServiceDetails;
import com.cts.exceptions.CarServiceNotFoundException;
import com.cts.repository.AddressRepository;
import com.cts.repository.CarServiceDetailsRepository;
import com.cts.service.CarServiceDetailsService;

/*
 * Implementation of the CarServiceDetailsService interface for managing car service records.
 */

@Service
public class CarServiceDetailServiceImplementation implements CarServiceDetailsService {

	private CarServiceDetailsRepository carServiceDetailsRepository;
	
	private AddressRepository addressRepository;

	public CarServiceDetailServiceImplementation(CarServiceDetailsRepository carServiceDetailsRepository,AddressRepository addressRepository) {
		super();
		this.carServiceDetailsRepository = carServiceDetailsRepository;
		this.addressRepository = addressRepository;
	}

	/*
	 * Retrieves all car service records from the database and maps them to DTOs.
	 * It return a list of CarServiceDetailsDto representing all service records    
	 */

	@Override
	public List<CarServiceDetailsDto> getAllService() {
		List<CarServiceDetails> carList = carServiceDetailsRepository.findAll();
		List<CarServiceDetailsDto> dto = new ArrayList<CarServiceDetailsDto>();
		for (CarServiceDetails c : carList) {
			CarServiceDetailsDto detailsDto = new CarServiceDetailsDto();
			detailsDto.setId(c.getId());
			detailsDto.setCarMake(c.getCarMake());
			detailsDto.setModelName(c.getModelName());
			detailsDto.setCarRegistrationNumber(c.getCarRegistrationNumber());
			detailsDto.setCarChassisNumber(c.getCarChassisNumber());
			detailsDto.setKnownIssues(c.getKnownIssues());
			detailsDto.setCost(c.getCost());
			detailsDto.setGivenDate(c.getGivenDate());
			detailsDto.setExpectedDeliveryDate(c.getExpectedDeliveryDate());
			detailsDto.setCreatedDateTime(c.getCreatedDateTime());
			detailsDto.setUpdatedDateTime(c.getUpdatedDateTime());
			detailsDto.setPhoneNumber(c.getPhoneNumber());
			if (c.getAddress() != null) {
				AddressDto addressDto = new AddressDto();
				addressDto.setId(c.getAddress().getId());
				addressDto.setHouseNumber(c.getAddress().getHouseNumber());
				addressDto.setStreet(c.getAddress().getStreet());
				addressDto.setLandmark(c.getAddress().getLandmark());
				addressDto.setCity(c.getAddress().getCity());
				addressDto.setState(c.getAddress().getState());
				addressDto.setPincode(c.getAddress().getPincode());
				detailsDto.setAddress(addressDto);
			}
			dto.add(detailsDto);
		}
		return dto;
	}

	/*
	 * Retrieves a car service record by its ID and maps it to a DTO.      
	 * It return the corresponding CarServiceDetailsDto      
	 * It throws CarServiceNotFoundException if no record is found with the given ID     
	 */
	@Override
	public CarServiceDetailsDto getById(int id) {

		CarServiceDetailsDto detailsDto = new CarServiceDetailsDto();
		AddressDto addressDto = new AddressDto();
		CarServiceDetails serviceDetails = new CarServiceDetails();

		serviceDetails = carServiceDetailsRepository.findById(id)
				.orElseThrow(() -> new CarServiceNotFoundException("Car Service Detail with id " + id + " not found"));
		detailsDto.setId(serviceDetails.getId());
		detailsDto.setCarMake(serviceDetails.getCarMake());
		detailsDto.setModelName(serviceDetails.getModelName());
		detailsDto.setCarRegistrationNumber(serviceDetails.getCarRegistrationNumber());
		detailsDto.setCarChassisNumber(serviceDetails.getCarChassisNumber());
		detailsDto.setKnownIssues(serviceDetails.getKnownIssues());
		detailsDto.setCost(serviceDetails.getCost());
		detailsDto.setGivenDate(serviceDetails.getGivenDate());
		detailsDto.setExpectedDeliveryDate(serviceDetails.getExpectedDeliveryDate());
		detailsDto.setCreatedDateTime(serviceDetails.getCreatedDateTime());
		detailsDto.setUpdatedDateTime(serviceDetails.getUpdatedDateTime());
		detailsDto.setPhoneNumber(serviceDetails.getPhoneNumber());
		if (serviceDetails.getAddress() != null) {
			addressDto.setId(serviceDetails.getAddress().getId());
			addressDto.setHouseNumber(serviceDetails.getAddress().getHouseNumber());
			addressDto.setStreet(serviceDetails.getAddress().getStreet());
			addressDto.setLandmark(serviceDetails.getAddress().getLandmark());
			addressDto.setCity(serviceDetails.getAddress().getCity());
			addressDto.setState(serviceDetails.getAddress().getState());
			addressDto.setPincode(serviceDetails.getAddress().getPincode());
			detailsDto.setAddress(addressDto);
		}

		return detailsDto;

	}

	/*
	 * Adds a new car service record to the database.      
	 * It return the saved CarServiceDetailsDto with generated ID and timestamps     
	 */

	@Override
	public CarServiceDetailsDto addCarService(CarServiceDetailsDto carServiceDetails) {

		CarServiceDetails details = new CarServiceDetails();
		Address address = new Address();
		details.setCarRegistrationNumber(carServiceDetails.getCarRegistrationNumber());
		details.setCarMake(carServiceDetails.getCarMake());
		details.setModelName(carServiceDetails.getModelName());
		details.setCarChassisNumber(carServiceDetails.getCarChassisNumber());
		details.setKnownIssues(carServiceDetails.getKnownIssues());
		details.setCost(carServiceDetails.getCost());
		details.setGivenDate(carServiceDetails.getGivenDate());
		details.setExpectedDeliveryDate(carServiceDetails.getExpectedDeliveryDate());
		details.setCreatedDateTime(LocalDateTime.now());
		details.setUpdatedDateTime(LocalDateTime.now());
		details.setPhoneNumber(carServiceDetails.getPhoneNumber());

		if (carServiceDetails.getAddress() != null) {

			address.setId(carServiceDetails.getAddress().getId());
			address.setHouseNumber(carServiceDetails.getAddress().getHouseNumber());
			address.setStreet(carServiceDetails.getAddress().getStreet());
			address.setLandmark(carServiceDetails.getAddress().getLandmark());
			address.setCity(carServiceDetails.getAddress().getCity());
			address.setState(carServiceDetails.getAddress().getState());
			address.setPincode(carServiceDetails.getAddress().getPincode());
			details.setAddress(address);

		}

		AddressDto addressDto = new AddressDto();
		CarServiceDetails addedCarDetails = carServiceDetailsRepository.save(details);
		carServiceDetails.setId(addedCarDetails.getId());
		carServiceDetails.setCreatedDateTime(addedCarDetails.getCreatedDateTime());
		carServiceDetails.setUpdatedDateTime(addedCarDetails.getUpdatedDateTime());
		addressDto.setId(addedCarDetails.getAddress().getId());
		addressDto.setHouseNumber(addedCarDetails.getAddress().getHouseNumber());
		addressDto.setStreet(addedCarDetails.getAddress().getStreet());
		addressDto.setLandmark(addedCarDetails.getAddress().getLandmark());
		addressDto.setCity(addedCarDetails.getAddress().getCity());
		addressDto.setState(addedCarDetails.getAddress().getState());
		addressDto.setPincode(addedCarDetails.getAddress().getPincode());

		carServiceDetails.setAddress(addressDto);

		return carServiceDetails;

	}

	/*
	 * Updates an existing car service record by its ID.      
	 * It return the updated CarServiceDetailsDto      
	 * It throws CarServiceNotFoundException if the record does not exist or no input is provided     
	 */

	@Override
	public CarServiceDetailsDto updateById(int id, CarServiceDetailsUpdateDto carServiceDetails) {
		if (!carServiceDetailsRepository.existsById(id)) {
			throw new CarServiceNotFoundException("Car service details with id " + id + " not found");
		}

		CarServiceDetails existing = carServiceDetailsRepository.findById(id).get();
		CarServiceDetailsUpdateDto csd = new CarServiceDetailsUpdateDto();

		if (carServiceDetails.equals(csd)) {
			throw new CarServiceNotFoundException("Please enter an input to update");
		}
		

		existing.setUpdatedDateTime(LocalDateTime.now());

		if ((carServiceDetails.getCarMake() != null)) {
			existing.setCarMake(carServiceDetails.getCarMake());
		} else {
			existing.setCarMake(existing.getCarMake());
		}
		if ((carServiceDetails.getModelName() == null) || (carServiceDetails.getModelName().equals("0"))) {

			existing.setModelName(existing.getModelName());
		} else {
			existing.setModelName(carServiceDetails.getModelName());
		}
		if (carServiceDetails.getCarRegistrationNumber() != null) {
			existing.setCarRegistrationNumber(carServiceDetails.getCarRegistrationNumber());
		} else {
			existing.setCarRegistrationNumber(existing.getCarRegistrationNumber());
		}
		if (carServiceDetails.getCarChassisNumber() != null) {
			existing.setCarChassisNumber(carServiceDetails.getCarChassisNumber());
		} else {
			existing.setCarChassisNumber(existing.getCarChassisNumber());
		}
		if ((carServiceDetails.getKnownIssues() == null) || (carServiceDetails.getKnownIssues().equals("0"))) {
			existing.setKnownIssues(existing.getKnownIssues());
		} else {

			existing.setKnownIssues(carServiceDetails.getKnownIssues());
		}
		if (carServiceDetails.getCost() != null) {
			existing.setCost(carServiceDetails.getCost());
		} else {
			existing.setCost(existing.getCost());
		}
		if (carServiceDetails.getGivenDate() != null) {
			existing.setGivenDate(carServiceDetails.getGivenDate());
		} else {
			existing.setGivenDate(existing.getGivenDate());
		}
		if (carServiceDetails.getExpectedDeliveryDate() != null) {
			existing.setExpectedDeliveryDate(carServiceDetails.getExpectedDeliveryDate());
		} else {
			existing.setExpectedDeliveryDate(existing.getExpectedDeliveryDate());
		}
		if (carServiceDetails.getPhoneNumber() != null) {
			existing.setPhoneNumber(carServiceDetails.getPhoneNumber());
		} else {
			existing.setPhoneNumber(existing.getPhoneNumber());
		}

		Address existingAddress = carServiceDetailsRepository.findById(id).get().getAddress();
		AddressUpdateDto newAddress = carServiceDetails.getAddress();
		if (newAddress != null) {
		

			if (newAddress.getHouseNumber()!=null) {
				
				existingAddress.setHouseNumber(newAddress.getHouseNumber());
			} else {
				existingAddress.setHouseNumber(existingAddress.getHouseNumber());
			}
			if (newAddress.getCity() != null) {
				existingAddress.setCity(newAddress.getCity());
			} else {
				existingAddress.setCity(existingAddress.getCity());
			}
			if (newAddress.getStreet()!=null) {

				existingAddress.setStreet(newAddress.getStreet());
			} else {
				existingAddress.setStreet(existingAddress.getStreet());
			}
			if ((newAddress.getLandmark() == null) && (newAddress.getLandmark() != null)) {
				existingAddress.setLandmark(existingAddress.getLandmark());
			} else {
				existingAddress.setLandmark(newAddress.getLandmark());
			}
			if (newAddress.getState() != null) {
				existingAddress.setState(newAddress.getState());
			} else {
				existingAddress.setState(existingAddress.getState());
			}
			if (newAddress.getPincode() != null) {
				existingAddress.setPincode(newAddress.getPincode());
			} else {
				existingAddress.setPincode(existingAddress.getPincode());
			}

			existing.setAddress(existingAddress);
		} else {
			existing.setAddress(existingAddress);
		}

		CarServiceDetails updatedCarDetails = carServiceDetailsRepository.save(existing);
		CarServiceDetailsDto updatedCarDetailsDto = new CarServiceDetailsDto();
		AddressDto updatedAddressDto = new AddressDto();
		updatedCarDetailsDto.setId(updatedCarDetails.getId());
		updatedCarDetailsDto.setCarMake(updatedCarDetails.getCarMake());
		updatedCarDetailsDto.setModelName(updatedCarDetails.getModelName());
		updatedCarDetailsDto.setCarRegistrationNumber(updatedCarDetails.getCarRegistrationNumber());
		updatedCarDetailsDto.setCarChassisNumber(updatedCarDetails.getCarChassisNumber());
		updatedCarDetailsDto.setKnownIssues(updatedCarDetails.getKnownIssues());
		updatedCarDetailsDto.setCost(updatedCarDetails.getCost());
		updatedCarDetailsDto.setGivenDate(updatedCarDetails.getGivenDate());
		updatedCarDetailsDto.setExpectedDeliveryDate(updatedCarDetails.getExpectedDeliveryDate());
		updatedCarDetailsDto.setPhoneNumber(updatedCarDetails.getPhoneNumber());
		updatedCarDetailsDto.setCreatedDateTime(updatedCarDetails.getCreatedDateTime());
		updatedCarDetailsDto.setUpdatedDateTime(updatedCarDetails.getUpdatedDateTime());

		updatedAddressDto.setId(updatedCarDetails.getAddress().getId());
		updatedAddressDto.setHouseNumber(updatedCarDetails.getAddress().getHouseNumber());
		updatedAddressDto.setStreet(updatedCarDetails.getAddress().getStreet());
		updatedAddressDto.setLandmark(updatedCarDetails.getAddress().getLandmark());
		updatedAddressDto.setCity(updatedCarDetails.getAddress().getCity());
		updatedAddressDto.setState(updatedCarDetails.getAddress().getState());
		updatedAddressDto.setPincode(updatedCarDetails.getAddress().getPincode());

		updatedCarDetailsDto.setAddress(updatedAddressDto);
		return updatedCarDetailsDto;
	}

	/*
	 * Deletes a car service record by its ID.      
	 * It throws CarServiceNotFoundException if the record does not exist     
	 */

	@Override
	public void deleteById(int id) {
 
		if (!carServiceDetailsRepository.existsById(id)) {
			throw new CarServiceNotFoundException("Car service details with id " + id + " not found");
		}
		carServiceDetailsRepository.deleteById(id);
 
	}
 

}
