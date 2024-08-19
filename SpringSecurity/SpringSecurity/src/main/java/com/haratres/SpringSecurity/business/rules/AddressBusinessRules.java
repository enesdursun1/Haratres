package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.AddressDal;
import com.haratres.SpringSecurity.entities.concretes.Address;
import com.haratres.SpringSecurity.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class AddressBusinessRules {

    @Autowired
    AddressDal addressDal;


    public void validateAddressType(String addressType){


        try {

            Address.AddressType.valueOf(addressType.toUpperCase(Locale.ROOT));
        }
        catch (IllegalArgumentException exception){

           throw new BusinessException("Invalid address type ! Please choose one of these types (Home, Office, Outdoor)");
          }

    }

    public void addressNameCanNotBeDuplicatedWhenInserted(String addressName,int userId){

      Address address =  addressDal.findByAddressNameAndUser_UserId(addressName,userId);

        if (address!=null){
            throw new BusinessException("Address name exists !");
        }
    }

    public void addressNameCanNotBeDuplicatedWhenUpdated(String addressName,int addressId,int userId){

        Address address=addressDal.findByAddressNameAndUser_UserId(addressName,userId);

        if (address != null && address.getAddressId() != addressId){
            throw new BusinessException("Address name exists !");
        }
    }
    public void addressShouldBeExistWhenSelected(int addressId){

        Optional<Address> address = addressDal.findById(addressId);

        if (!address.isPresent())
            throw new BusinessException("Address not exists !");
    }


}
