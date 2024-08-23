package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import com.haratres.SpringSecurity.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductDal extends JpaRepository<CartProduct, Integer> {

    boolean existsByProduct_ProductIdAndCart_CartId(int productId, int cartId);
    CartProduct findByProduct_ProductIdAndCart_CartId(int productId, int cartId);
    List<CartProduct> findByCart_CartId(int cartId);
    void deleteByCart_CartId(int cartId);

}
