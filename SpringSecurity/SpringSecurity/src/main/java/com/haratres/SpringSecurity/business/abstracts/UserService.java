package com.haratres.SpringSecurity.business.abstracts;



import com.haratres.SpringSecurity.business.dtos.user.UserRegisterRequestDto;
import com.haratres.SpringSecurity.entities.concretes.User;



public interface UserService {

	User getByUsername(String username);
	User register(UserRegisterRequestDto userRegisterRequest);
}
