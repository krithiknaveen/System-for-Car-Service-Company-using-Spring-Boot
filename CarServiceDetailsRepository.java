package com.cts.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Address;
import com.cts.entity.CarServiceDetails;

/*
 * Repository interface for performing CRUD operations on the CarServiceDetails entity.
 */

public interface CarServiceDetailsRepository extends JpaRepository<CarServiceDetails, Integer>{
	
	int countByAddress(Address address);


}
