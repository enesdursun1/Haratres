package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.dtos.user.RegisteredUserResponse;
import com.haratres.SpringSecurity.business.rules.UserBusinessRules;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperManager;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.RoleDal;
import com.haratres.SpringSecurity.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.RegisterUserRequest;
import com.haratres.SpringSecurity.dataAccess.abstracts.UserDal;
import com.haratres.SpringSecurity.entities.concretes.User;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private RoleDal roleDal;


	@Override
	public User getByUsername(String username) {

		return userDal.findByUsername(username);
	}

	@Override
	public User getById(int userId) {

		return  userDal.findById(userId).get();
	}

	public RegisteredUserResponse register(RegisterUserRequest userRegisterRequest) {

		    String password = userRegisterRequest.getPassword();

            userBusinessRules.userNameCanNotBeDuplicated(userRegisterRequest.getUsername());

	    	userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

		    Role userRole = roleDal.findByRoleName("ROLE_USER");

			User user = new User(userRegisterRequest.getUsername(),userRegisterRequest.getPassword(),new ArrayList<>(){{add(userRole);}});

	    	User registeredUser =  userDal.save(user);

			RegisteredUserResponse response = modelMapperService.forResponse().map(registeredUser, RegisteredUserResponse.class);

			response.setPassword(password);

			return response;

}

}
