package com.cts.repository;

import java.util.Optional;


/*
 * Repository interface for managing Users entities in the database.
 */

import org.springframework.data.jpa.repository.JpaRepository;


import com.cts.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByUsername(String username);
	Optional<Users> findByEmail(String email);
	Optional<Users> findByUsernameOrEmail(String username,String email);
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsernameOrEmail(String username,String email);

}
