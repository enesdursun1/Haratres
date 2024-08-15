package com.haratres.SpringSecurity.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haratres.SpringSecurity.entities.concretes.Role;

@Repository
public interface RoleDal extends JpaRepository<Role, Integer> {

	
}
