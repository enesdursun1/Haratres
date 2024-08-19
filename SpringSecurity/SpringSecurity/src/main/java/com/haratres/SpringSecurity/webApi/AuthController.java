package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.dtos.user.RegisteredUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.RegisterUserRequest;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test () {
		
		return "testt";
		
	}

	  @PostMapping("/register")
	    public RegisteredUserResponse register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {



		  return  userService.register(registerUserRequest);
	    }
	 
}
