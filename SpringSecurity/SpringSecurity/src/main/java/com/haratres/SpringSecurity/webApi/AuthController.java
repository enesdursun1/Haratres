package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.dtos.user.RegisteredUserResponse;
import com.haratres.SpringSecurity.core.security.jwt.JwtHelper;
import com.haratres.SpringSecurity.core.security.jwt.LoginRequest;
import com.haratres.SpringSecurity.entities.concretes.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.RegisterUserRequest;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtHelper.generateToken((CustomUserDetail) authentication.getPrincipal());

		return ResponseEntity.ok(token);
	}

	  @PostMapping("/register")
	    public RegisteredUserResponse register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {

		  return  userService.register(registerUserRequest);
	    }
	 
}
