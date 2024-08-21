package com.haratres.SpringSecurity.dataAccess.abstracts;

import com.haratres.SpringSecurity.entities.concretes.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDal extends JpaRepository<Stock, Integer> {

    Stock findByProduct_ProductId(int productId);
    boolean existsByProduct_ProductId(int productId);
}
