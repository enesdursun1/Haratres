package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.ProductDal;
import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductBusinessRules {

    @Autowired
    private ProductDal productDal;
    @Autowired
    private CategoryBusinessRules categoryBusinessRules;

    public void categoryShouldBeExistWhenSelected(int categoryId) {
       categoryBusinessRules.categoryShouldBeExistWhenSelected(categoryId);
    }


    public void productNameCanNotBeDuplicatedWhenInserted(String productName){
        Product product= productDal.findByProductName(productName);
        if (product != null){
            throw new BusinessException("Product name exists !");
        }
    }
    public void productNameCanNotBeDuplicatedWhenUpdated(String productName,int productId){
        Product product= productDal.findByProductName(productName);
        if (product != null && product.getProductId() !=productId){
            throw new BusinessException("Product name exists !");
        }
    }
    public void productShouldBeExistWhenSelected(Optional<Product> product)
    {

        if (!product.isPresent())
            throw new BusinessException("Product not exists !");
    }

    public void productShouldBeExistWhenSearch(Product product)
    {
        if (product == null)
            throw new BusinessException("Product not exists !");
    }

    //İngilizce karakterlerden dolayı büyük I'yı lower yaparken i olarak çeviriyor.Bunu düzeltmek için yapıldı.
    public String productNameToLowerCaseForSearch(String productName)
    {
        for (int i = 0; i < productName.length() ; i++) {

           if(productName.toCharArray()[i]=='I') productName.toCharArray()[i]='i';
        }
    return productName.toLowerCase();

    }

}
