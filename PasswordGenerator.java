package com.cts.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * The PasswordGenerator class is a simple utility for generating 
 * BCrypt-hashed passwords using Spring Security's BCryptPasswordEncoder.
 */ 

public class PasswordGenerator {
	
	public static void main(String[] args) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("Krithik@231"));
		System.out.println(bCryptPasswordEncoder.encode("Nivedhitha@123"));
	}
	

}
