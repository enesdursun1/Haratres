package com.haratres.SpringSecurity.entities.concretes;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {

	private int userId;
	private String username;
	private String password;
	
	private List<GrantedAuthority> roles;
	
	
	public CustomUserDetail(User user) {
		this.userId = user.getUserId();
		this.username=user.getUsername();
		this.password=user.getPassword();
	    this.roles=user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public int getUserId() {

		return userId;
	}

}
