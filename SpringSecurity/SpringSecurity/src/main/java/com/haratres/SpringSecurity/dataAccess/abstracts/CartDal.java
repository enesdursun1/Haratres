package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDal extends JpaRepository<Cart, Integer> {

    Cart getByUser_UserId(int userId);
    boolean existsByUser_UserId(int userId);
    void deleteByUser_UserId(int userId);
}
