package com.cts.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dtos.AddressDto;
import com.cts.dtos.AddressUpdateDto;
import com.cts.dtos.CarServiceDetailsDto;
import com.cts.dtos.CarServiceDetailsUpdateDto;
import com.cts.entity.Address;
import com.cts.entity.CarServiceDetails;
import com.cts.exceptions.CarServiceNotFoundException;
import com.cts.repository.CarServiceDetailsRepository;

/*
 * Unit test class for CarServiceDetailServiceImplementation, which handles
 * the business logic related to car service details.
 */

@SpringBootTest
class CarServiceDetailServiceImplementationTest {

	@Mock
	CarServiceDetailsRepository carServiceDetailsRepository;

	@InjectMocks
	CarServiceDetailServiceImplementation carServiceDetailService;

	CarServiceDetails car1 = new CarServiceDetails();
	Address address1 = new Address();
	CarServiceDetails car2 = new CarServiceDetails();
	Address address2 = new Address();
	CarServiceDetails car3 = new CarServiceDetails();
	Address address3 = new Address();
	List<CarServiceDetails> carServiceDetails;
	CarServiceDetailsUpdateDto car1UpdateDto = new CarServiceDetailsUpdateDto();
	AddressUpdateDto address1UpdateDto = new AddressUpdateDto();

	CarServiceDetailsDto car1Dto = new CarServiceDetailsDto();
	AddressDto address1Dto = new AddressDto();

	/*
	 * Initializes test data before each test case. This setup ensures consistent
	 * and reusable test data across all test cases.
	 */

	@BeforeEach
	void init() {

		car1.setId(1);
		car1.setCarMake("Hyundai");
		car1.setModelName("Creta");
		car1.setCarRegistrationNumber("TN-22-AB-1234");
		car1.setKnownIssues("AC not cooling, Brake noise");
		car1.setCarChassisNumber("MALBM51BLFM123456");
		car1.setCost(new BigDecimal("8500.22"));
		car1.setGivenDate(LocalDate.of(2025, 5, 20));
		car1.setExpectedDeliveryDate(LocalDate.of(2025, 5, 27));
		car1.setCreatedDateTime(LocalDateTime.now());
		car1.setUpdatedDateTime(LocalDateTime.now());
		car1.setPhoneNumber("9894365420");

		address1.setId(1);
		address1.setHouseNumber("12A");
		address1.setStreet("Anna Nagar Main Road");
		address1.setLandmark("Near Tower Park");
		address1.setCity("Chennai");
		address1.setState("Tamil Nadu");
		address1.setPincode("600040");

		car1.setAddress(address1);

		car2.setId(2);
		car2.setCarMake("Maruti Suzuki");
		car2.setModelName("Swift");
		car2.setCarRegistrationNumber("TN-10-CD-5678");
		car2.setKnownIssues("Engine overheating, Oil leakage");
		car2.setCarChassisNumber("MA3EHB12S00765432");
		car2.setCost(new BigDecimal("12500.7"));
		car2.setGivenDate(LocalDate.of(2025, 5, 18));
		car2.setExpectedDeliveryDate(LocalDate.of(2025, 5, 24));
		car2.setCreatedDateTime(LocalDateTime.now());
		car2.setUpdatedDateTime(LocalDateTime.now());
		car2.setPhoneNumber("9962012345");

		address2.setId(2);
		address2.setHouseNumber("45B");
		address2.setStreet("Velachery Main Road");
		address2.setLandmark("Opposite Phoenix Mall");
		address2.setCity("Chennai");
		address2.setState("Tamil Nadu");
		address2.setPincode("600042");

		car2.setAddress(address2);

		car3.setId(3);
		car3.setCarMake("Toyota");
		car3.setModelName("Innova Crysta");
		car3.setCarRegistrationNumber("TN-07-EF-9012");
		car3.setKnownIssues("Suspension noise, Dashboard lights flickering");
		car3.setCarChassisNumber("MBJAXXGJXK1234567");
		car3.setCost(new BigDecimal("18900.4"));
		car3.setGivenDate(LocalDate.of(2025, 5, 22));
		car3.setExpectedDeliveryDate(LocalDate.of(2025, 5, 30));
		car3.setCreatedDateTime(LocalDateTime.now());
		car3.setUpdatedDateTime(LocalDateTime.now());
		car3.setPhoneNumber("9789012345");

		address3.setId(3);
		address3.setHouseNumber("89C");
		address3.setStreet("OMR Road");
		address3.setLandmark("Near Tidel Park");
		address3.setCity("Chennai");
		address3.setState("Tamil Nadu");
		address3.setPincode("600113");

		car3.setAddress(address3);

		carServiceDetails = new ArrayList<CarServiceDetails>();
		carServiceDetails.add(car1);
		carServiceDetails.add(car2);
		carServiceDetails.add(car3);

		car1Dto.setId(car1.getId());
		car1Dto.setCarMake(car1.getCarMake());
		car1Dto.setModelName(car1.getModelName());
		car1Dto.setCarRegistrationNumber(car1.getCarRegistrationNumber());
		car1Dto.setKnownIssues(car1.getKnownIssues());
		car1Dto.setCarChassisNumber(car1.getCarChassisNumber());
		car1Dto.setCost(car1.getCost());
		car1Dto.setGivenDate(car1.getGivenDate());
		car1Dto.setExpectedDeliveryDate(car1.getExpectedDeliveryDate());
		car1Dto.setCreatedDateTime(car1.getCreatedDateTime());
		car1Dto.setUpdatedDateTime(car1.getUpdatedDateTime());
		car1Dto.setPhoneNumber(car1.getPhoneNumber());

		address1Dto.setId(address1.getId());
		address1Dto.setHouseNumber(address1.getHouseNumber());
		address1Dto.setStreet(address1.getStreet());
		address1Dto.setLandmark(address1.getLandmark());
		address1Dto.setCity(address1.getCity());
		address1Dto.setState(address1.getState());
		address1Dto.setPincode(address1.getPincode());

		car1Dto.setAddress(address1Dto);

		car1UpdateDto.setId(car1.getId());
		car1UpdateDto.setCarMake(car1.getCarMake());
		car1UpdateDto.setModelName(car1.getModelName());
		car1UpdateDto.setCarRegistrationNumber(car1.getCarRegistrationNumber());
		car1UpdateDto.setKnownIssues(car1.getKnownIssues());
		car1UpdateDto.setCarChassisNumber(car1.getCarChassisNumber());
		car1UpdateDto.setCost(car1.getCost());
		car1UpdateDto.setGivenDate(car1.getGivenDate());
		car1UpdateDto.setExpectedDeliveryDate(car1.getExpectedDeliveryDate());
		car1UpdateDto.setCreatedDateTime(car1.getCreatedDateTime());
		car1UpdateDto.setUpdatedDateTime(car1.getUpdatedDateTime());
		car1UpdateDto.setPhoneNumber(car1.getPhoneNumber());

		address1UpdateDto.setId(address1.getId());
		address1UpdateDto.setHouseNumber(address1.getHouseNumber());
		address1UpdateDto.setStreet(address1.getStreet());
		address1UpdateDto.setLandmark(address1.getLandmark());
		address1UpdateDto.setCity(address1.getCity());
		address1UpdateDto.setState(address1.getState());
		address1UpdateDto.setPincode(address1.getPincode());

		car1UpdateDto.setAddress(address1UpdateDto);

	}

	/*
	 * Tests the getAllService() method of the service implementation.
	 * 
	 * Verifies that the service correctly retrieves all car service records from
	 * the repository and maps them to DTOs.
	 */

	@Test
	void testGetAllService() {

		when(carServiceDetailsRepository.findAll()).thenReturn(carServiceDetails);
		List<CarServiceDetailsDto> carService = carServiceDetailService.getAllService();
		assertEquals(3, carService.size());
		assertEquals("Hyundai", carService.get(0).getCarMake());
		verify(carServiceDetailsRepository, times(1)).findAll();
	}

	/*
	 * Tests the getById(int id) method for a valid car service ID.
	 * 
	 * Ensures that when a valid ID is provided, the service retrieves the
	 * corresponding car service record and maps it to a DTO correctly.
	 */

	@Test
	void testGetById() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(true);
		when(carServiceDetailsRepository.findById(anyInt())).thenReturn(Optional.of(car1));
		CarServiceDetailsDto carTest = carServiceDetailService.getById(1);
		assertNotNull(carTest);
		assertEquals("Hyundai", carTest.getCarMake());
		verify(carServiceDetailsRepository, times(1)).findById(anyInt());
	}

	/*
	 * Tests the getById(int id) method for a non-existent car service ID.
	 * 
	 * Verifies that the service throws a CarServiceNotFoundException
	   when the provided ID does not exist in the repository.
	 */

	@Test
	void testGetCarServiceNotFound() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(false);
		assertThrows(CarServiceNotFoundException.class, () -> carServiceDetailService.getById(5));
		verify(carServiceDetailsRepository, times(1)).findById(5);
		verify(carServiceDetailsRepository, never()).existsById(5);

	}

	/*
	 * Tests the addCarService() method of the service implementation.
	 *
	 * Verifies that a new car service record is saved correctly and the returned
	 * DTO contains the expected values.
	 */

	@Test
	void testAddCarService() {
		when(carServiceDetailsRepository.save(any(CarServiceDetails.class))).thenReturn(car1);
		CarServiceDetailsDto carTest = carServiceDetailService.addCarService(car1Dto);
		assertNotNull(carTest);
		assertEquals("Hyundai", carTest.getCarMake());
		assertEquals(new BigDecimal("8500.22"), carTest.getCost());
		verify(carServiceDetailsRepository, times(1)).save(any(CarServiceDetails.class));
	}

	/*
	 * Tests the updateById() method for a valid car service ID.
	 * Ensures that the service updates the existing record and returns the updated DTO.
	 */

	@Test
	void testUpdateById() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(true);
		when(carServiceDetailsRepository.findById(anyInt())).thenReturn(Optional.of(car1));
		when(carServiceDetailsRepository.save(any(CarServiceDetails.class))).thenReturn(car1);

		CarServiceDetailsDto carTest = carServiceDetailService.updateById(4, car1UpdateDto);

		assertNotNull(carTest);
		assertEquals("MALBM51BLFM123456", carTest.getCarChassisNumber());
		verify(carServiceDetailsRepository, times(1)).existsById(4);
		verify(carServiceDetailsRepository, times(2)).findById(4);
		verify(carServiceDetailsRepository, times(1)).save(car1);
	}

	/*
	 * Tests the updateById() method when the car service ID does not exist.
	 * Verifies that a CarServiceNotFoundException is thrown and that the
	 * repository's save() method is never called.
	 */

	@Test
	void testUpdateCarServiceNotFound() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(false);
		assertThrows(CarServiceNotFoundException.class, () -> carServiceDetailService.updateById(5, car1UpdateDto));
		verify(carServiceDetailsRepository, never()).save(car1);
		verify(carServiceDetailsRepository, times(1)).existsById(5);
	}

	/*
	 * Tests the deleteById() method for a valid car service ID.
	 *
	 * Ensures that the service deletes the record when it exists.
	 */

	@Test
	void testDeleteById() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(true);
		when(carServiceDetailsRepository.findById(anyInt())).thenReturn(Optional.of(car2));
		carServiceDetailService.deleteById(2);
		verify(carServiceDetailsRepository, times(1)).deleteById(2);

	}

	/*
	 * Tests the deleteById() method when the car service ID does not exist.
	 *
	 * Verifies that a CarServiceNotFoundException is thrown and that the repository's 
	   deleteById() method is never called.
	 */

	@Test
	void testDeleteCarServiceNotFound() {
		when(carServiceDetailsRepository.existsById(anyInt())).thenReturn(false);
		assertThrows(CarServiceNotFoundException.class, () -> carServiceDetailService.deleteById(5));
		verify(carServiceDetailsRepository, never()).deleteById(1);
	}

}
