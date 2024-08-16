package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartProductDal;
import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartProductBusinessRules {

    @Autowired
    private CartProductDal cartProductDal;


    public void cartProductShouldBeExistWhenSelected(int cartProductId)
    {
        Optional<CartProduct> cartProduct = cartProductDal.findById(cartProductId);

        if (!cartProduct.isPresent())
            throw new BusinessException("Cart Product not exists !");
    }
    public void isCartEmptyForUser(int userId)
    {
        if (!cartProductDal.existsByUser_UserId(userId))
            throw new BusinessException("Cart Product not exists for this user !");
    }


  public boolean checkProductInCart(int productId,int userId){

      if (!cartProductDal.existsByProduct_ProductIdAndUser_UserId(productId,userId)) return  false;
      else return true ;


    }

}
