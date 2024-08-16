package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import com.haratres.SpringSecurity.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductDal extends JpaRepository<CartProduct, Integer> {

    List<CartProduct> findByUser_UserId(int userId);
    void deleteByUser_UserId(int userId);
    CartProduct findByProduct_ProductIdAndUser_UserId(int cartProductId, int userId);
    boolean existsByProduct_ProductIdAndUser_UserId(int productId, int userId);
    boolean existsByUser_UserId(int userId);
}
