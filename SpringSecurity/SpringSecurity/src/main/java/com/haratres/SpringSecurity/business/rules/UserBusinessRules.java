package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.AddressDal;
import com.haratres.SpringSecurity.dataAccess.abstracts.UserDal;
import com.haratres.SpringSecurity.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessRules {

    @Autowired
    UserDal userDal;


    public void userNameCanNotBeDuplicated(String userName){

        if (userDal.existsByUsername(userName)){

            throw new BusinessException("User name exists !");

        }
    }



}
