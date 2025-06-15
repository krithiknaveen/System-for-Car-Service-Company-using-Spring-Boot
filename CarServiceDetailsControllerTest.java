package com.cts.controller;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.dtos.AddressDto;
import com.cts.dtos.CarServiceDetailsDto;
import com.cts.dtos.CarServiceDetailsUpdateDto;
import com.cts.entity.Address;
import com.cts.entity.CarServiceDetails;
import com.cts.service.CarServiceDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * Integration test class for CarServiceDetailsController.
 *
 * This class uses Spring Boot's testing support along with MockMvc to simulate HTTP requests
   and validate the behavior of the REST controller endpoints for car service operations.
 */

@SpringBootTest
@AutoConfigureMockMvc
class CarServiceDetailsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private CarServiceDetailsService carServiceDetailsService;
	
	@Autowired
	private ObjectMapper mapper;
	
	CarServiceDetails car1 = new CarServiceDetails();
	Address address1 = new Address();
	CarServiceDetails car2 = new CarServiceDetails();
	Address address2 = new Address();
	CarServiceDetails car3 = new CarServiceDetails();
	Address address3 = new Address();
	CarServiceDetailsDto car4 = new CarServiceDetailsDto();
	AddressDto address4 = new AddressDto();
	List<CarServiceDetails> carServiceDetails;
	List<CarServiceDetailsDto> carServiceDetailsDtos;
	
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
		car1.setCost(new BigDecimal("1234.34"));
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
		car2.setCost(new BigDecimal("12500.00"));
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
		car3.setCost(new BigDecimal("18900.89"));
		car3.setGivenDate(LocalDate.of(2025, 5, 23));
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
		
		car4.setId(4);
		car4.setCarMake("Ferrari");
		car4.setModelName("488 GTB");
		car4.setCarRegistrationNumber("TN-14-FR-1234");
		car4.setKnownIssues("Turbo lag, Rear diffuser damage");
		car4.setCarChassisNumber("ZFF79ALA4H0223456");
		car4.setCost(new BigDecimal("69500.20"));
		car4.setGivenDate(LocalDate.of(2025, 5, 21));
		car4.setExpectedDeliveryDate(LocalDate.of(2025, 5, 28));
		car4.setCreatedDateTime(LocalDateTime.now());
		car4.setUpdatedDateTime(LocalDateTime.now());
		car4.setPhoneNumber("9845012345");

		address4.setId(4);
		address4.setHouseNumber("21");
		address4.setStreet("Nungambakkam High Road");
		address4.setLandmark("Near Taj Coromandel");
		address4.setCity("Chennai");
		address4.setState("Tamil Nadu");
		address4.setPincode("600034");

		car4.setAddress(address4);

		
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
		carServiceDetailsDtos = new ArrayList<CarServiceDetailsDto>();
		carServiceDetailsDtos.add(car1Dto);
		carServiceDetailsDtos.add(car4);


		
	}

	/*
	 * Integration test class for link CarServiceDetailsController.
	 *
	 * This class uses Spring Boot's testing support along with MockMvc to simulate
	 * HTTP requests and validate the behavior of the REST controller endpoints for
	   car service operations.
	 */

	@Test
	@WithMockUser
	void testGetAll() throws Exception {
		when(carServiceDetailsService.getAllService()).thenReturn(carServiceDetailsDtos);
		mockMvc.perform(get("/api/carservice")).andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(2))
		.andExpect(jsonPath("$[1].carMake").value("Ferrari"));
		verify(carServiceDetailsService,times(1)).getAllService();
	}

	/*
	 * Tests the GET endpoint {/api/carservice/{id}} to retrieve a car service record by ID.
	 *
	 * Mocks the service layer to return a specific DTO
	 */
	
	@Test
	@WithMockUser
	void testGetById() throws Exception {
		when(carServiceDetailsService.getById(anyInt())).thenReturn(car4);
		mockMvc.perform(get("/api/carservice/4")).andExpect(status().isOk())
		.andExpect(jsonPath("$.carMake",is("Ferrari"))).andExpect(jsonPath("$.cost",is(69500.20)));
	}

	/*
	 * Tests the POST endpoint {@code /api/carservice/save} to add a new car service record.
	 * Mocks the service layer to return the saved DTO and verifies:
	 * <ul>
	 */
	
	@Test
	@WithMockUser(roles = "ADMIN")
	void testAddNewService() throws Exception {
		when(carServiceDetailsService.addCarService(any(CarServiceDetailsDto.class))).thenReturn(car1Dto);
		
		var jsonCarService = mapper.writeValueAsString(car1Dto);
		
		mockMvc.perform(post("/api/carservice/save").content(jsonCarService).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andExpect(jsonPath("$.carMake", is("Hyundai")));
	}

	/*
	 * Tests the PUT endpoint {@code /api/carservice/{id}} to update an existing car service record.
	 * Mocks the service layer to return the updated DTO
	 */
	
	@Test
	@WithMockUser(roles = "ADMIN")
	void testUpdateService() throws Exception {
		
		when(carServiceDetailsService.updateById(anyInt(), any(CarServiceDetailsUpdateDto.class))).thenReturn(car1Dto);
		
		var jsonCarService = mapper.writeValueAsString(car1Dto);
		
		mockMvc.perform(put("/api/carservice/1").content(jsonCarService).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andExpect(jsonPath("$.carMake", is("Hyundai")));
		
	}

	/*
	 * Tests the DELETE endpoint {@code /api/carservice/{id}} to delete a car service record by ID.
	 * Mocks the service layer to perform no action
	 */

	@Test
	@WithMockUser(roles = "ADMIN")
	void testDeleteById() throws Exception {
		
		doNothing().when(carServiceDetailsService).deleteById(anyInt());
		mockMvc.perform(delete("/api/carservice/1")).andExpect(status().isAccepted());
	}

}
