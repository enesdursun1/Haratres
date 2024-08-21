package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.StockDal;
import com.haratres.SpringSecurity.entities.concretes.Price;
import com.haratres.SpringSecurity.entities.concretes.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockBusinessRules {

    @Autowired
    private StockDal stockDal;


    public void stockShouldBeExistWhenSelected(Optional<Stock> stock)
    {

        if (!stock.isPresent())
            throw new BusinessException("Stock not exists !");
    }

    public void isStockEmptyForProduct(int productId)
    {
        if (!stockDal.existsByProduct_ProductId(productId))
            throw new BusinessException("Stock information not exists for this product !");
    }
}
