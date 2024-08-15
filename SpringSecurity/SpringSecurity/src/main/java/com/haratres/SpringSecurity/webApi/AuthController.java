package com.haratres.SpringSecurity.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.UserRegisterRequestDto;
import com.haratres.SpringSecurity.entities.concretes.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test () {
		
		return "testt";
		
	}
	@GetMapping("/deneme")
	public String deneme () {
		
		return "deneme1";
		
	}
	  @PostMapping("/register")
	    public User register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
	    
		  return  userService.register(userRegisterRequestDto);
	    }
	 
}
