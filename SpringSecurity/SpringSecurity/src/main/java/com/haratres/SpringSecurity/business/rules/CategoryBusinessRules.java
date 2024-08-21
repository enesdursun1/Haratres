package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.CategoryDal;
import com.haratres.SpringSecurity.entities.concretes.Category;
import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryBusinessRules {
    @Autowired
    private CategoryDal categoryDal;

    public void categoryNameCanNotBeDuplicatedWhenInserted(String categoryName){

         Category category=categoryDal.findByCategoryName(categoryName);
        if (category != null){
            throw new BusinessException("Category name exists !");
        }
    }
    public void categoryNameCanNotBeDuplicatedWhenUpdated(String categoryName,int categoryId){

        Category category=categoryDal.findByCategoryName(categoryName);

        if (category != null && category.getCategoryId() != categoryId){
            throw new BusinessException("Category name exists !");
        }
    }
    public void categoryShouldBeExistWhenSelected(int categoryId)
    {
        Optional<Category> category = categoryDal.findById(categoryId);

        if (!category.isPresent())
            throw new BusinessException("Category not exists !");
    }
    public List<Category> paginationProcess(PageInfo pageInfo){

        if (pageInfo != null){

            PageRequest pageRequest = PageRequest.of(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return categoryDal.findAll(pageRequest).getContent();
        }
        else  return categoryDal.findAll();
    }

}
