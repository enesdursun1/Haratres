package com.haratres.SpringSecurity.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.entities.concretes.CustomUserDetail;
import com.haratres.SpringSecurity.entities.concretes.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
	private UserService userService; 	  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user =  userService.getByUsername(username);
		return new CustomUserDetail(user);
	}

}