package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.Category;
import com.haratres.SpringSecurity.entities.concretes.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceDal extends JpaRepository<Price, Integer> {

    Price findByProduct_ProductId(int productId);
    boolean existsByProduct_ProductId(int productId);


}
