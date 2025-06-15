package com.cts.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Address;

/*
 * Repository interface for performing CRUD operations on the Address entity.
 */

public interface AddressRepository extends JpaRepository<Address, Integer>{
	

}
