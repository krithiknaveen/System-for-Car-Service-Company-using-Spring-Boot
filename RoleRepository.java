package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Roles;


/*
 * Repository interface for managing Roles entities in the database.
 */

public interface RoleRepository extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByName(String name);

	boolean existsByName(String name);

}
