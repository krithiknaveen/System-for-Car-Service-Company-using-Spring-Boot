package com.cts.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.entity.Users;
import com.cts.repository.UserRepository;

/*
 * Service class that implements Spring Security's UserDetailsService interface.
 */

@Service
public class CarUserServiceDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;

	public CarUserServiceDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	/*
     * Loads the user details by username or email.
     *
     * This method is called by Spring Security during the authentication process.
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		List<GrantedAuthority> authorities = user.getRole().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}

}
