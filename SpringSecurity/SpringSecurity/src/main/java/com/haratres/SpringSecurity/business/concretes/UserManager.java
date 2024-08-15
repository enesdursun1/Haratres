package com.haratres.SpringSecurity.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.user.UserRegisterRequestDto;
import com.haratres.SpringSecurity.dataAccess.abstracts.UserDal;
import com.haratres.SpringSecurity.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	@Autowired
	private UserDal userDal; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	@Override
	public User getByUsername(String username) {
		
		return userDal.findByUsername(username);
	}


	    public User register(UserRegisterRequestDto userRegisterRequest) {

	    	userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
	        
	    	return userDal.save(new User(userRegisterRequest.getUsername(),userRegisterRequest.getPassword()));
}

}
