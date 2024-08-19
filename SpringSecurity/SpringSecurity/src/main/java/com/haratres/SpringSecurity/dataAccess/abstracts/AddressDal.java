package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDal extends JpaRepository<Address, Integer> {

    Address findByAddressNameAndUser_UserId(String addressName, int userId);
    List<Address> findAllByUser_UserId(int userId);

}
