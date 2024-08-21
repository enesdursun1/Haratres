package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.PriceDal;
import com.haratres.SpringSecurity.entities.concretes.Price;
import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PriceBusinessRules {

    @Autowired
    private PriceDal priceDal;


    public void priceShouldBeExistWhenSelected(Optional<Price> price)
    {

        if (!price.isPresent())
            throw new BusinessException("Price not exists !");
    }

    public void isPriceEmptyForProduct(int productId)
    {
        if (!priceDal.existsByProduct_ProductId(productId))
            throw new BusinessException("Price information not exists for this product !");
    }

}
