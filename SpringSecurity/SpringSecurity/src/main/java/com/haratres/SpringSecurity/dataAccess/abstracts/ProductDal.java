package com.haratres.SpringSecurity.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductDal extends JpaRepository<Product, Integer>{

    Product findByProductName(String productName);
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) = (:word) OR LOWER(p.productCode) = (:word)")
    Product findByProductNameOrProductCode(@Param("word") String keyword);
    boolean existsByProductCode(String productCode);
}
