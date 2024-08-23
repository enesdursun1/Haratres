package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CartBusinessRules {

    @Autowired
    private CartDal cartDal;

    public void cartShouldBeExistsWhenSelected(int userId){

        if (!cartDal.existsByUser_UserId(userId)) throw  new BusinessException("Cart empty for the user");


    }

}
