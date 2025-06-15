package com.cts.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


/*
 * Entity class representing a user in the system.
 *
 * This class is mapped to the "users" table in the database and is used to store
 * user account information including credentials and assigned roles.
 */

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String username;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Roles> role;

}
