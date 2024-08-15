package com.haratres.SpringSecurity.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haratres.SpringSecurity.entities.concretes.Product;



public interface ProductDal extends JpaRepository<Product, Integer>{

    Product findByProductName(String productName);
}
