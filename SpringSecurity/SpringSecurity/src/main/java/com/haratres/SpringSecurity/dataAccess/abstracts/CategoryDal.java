package com.haratres.SpringSecurity.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haratres.SpringSecurity.entities.concretes.Category;

public interface CategoryDal extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName);
}
