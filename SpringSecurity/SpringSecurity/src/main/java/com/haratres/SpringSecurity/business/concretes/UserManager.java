package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.dtos.user.RegisteredUserResponse;
import com.haratres.SpringSecurity.business.rules.UserBusinessRules;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperManager;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.RegisterUserRequest;
import com.haratres.SpringSecurity.dataAccess.abstracts.UserDal;
import com.haratres.SpringSecurity.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	@Autowired
	private UserDal userDal; 
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapperService modelMapperService;
	@Autowired
	private UserBusinessRules userBusinessRules;


	@Override
	public User getByUsername(String username) {

		return userDal.findByUsername(username);
	}

	public RegisteredUserResponse register(RegisterUserRequest userRegisterRequest) {

		    String password = userRegisterRequest.getPassword();

            userBusinessRules.userNameCanNotBeDuplicated(userRegisterRequest.getUsername());

	    	userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
	        
	    	User user =  userDal.save(new User(userRegisterRequest.getUsername(),userRegisterRequest.getPassword()));

			RegisteredUserResponse response = modelMapperService.forResponse().map(user, RegisteredUserResponse.class);

			response.setPassword(password);

			return response;

}

}
