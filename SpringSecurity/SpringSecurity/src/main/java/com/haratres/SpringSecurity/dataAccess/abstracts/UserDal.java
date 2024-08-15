package com.haratres.SpringSecurity.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haratres.SpringSecurity.entities.concretes.User;

@Repository
public interface UserDal extends JpaRepository<User,Integer> {



		User findByUsername(String username);


}
