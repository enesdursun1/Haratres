package com.haratres.SpringSecurity.business.abstracts;



import com.haratres.SpringSecurity.business.dtos.user.RegisterUserRequest;
import com.haratres.SpringSecurity.business.dtos.user.RegisteredUserResponse;
import com.haratres.SpringSecurity.entities.concretes.User;



public interface UserService {

	User getByUsername(String username);
	User getById(int userId);
	RegisteredUserResponse register(RegisterUserRequest userRegisterRequest);

}
