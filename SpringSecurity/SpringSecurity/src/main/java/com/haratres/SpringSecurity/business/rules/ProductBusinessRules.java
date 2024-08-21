package com.haratres.SpringSecurity.business.rules;

import com.haratres.SpringSecurity.business.utilities.BarcodeGenerator;
import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.core.utilites.exceptions.types.BusinessException;
import com.haratres.SpringSecurity.dataAccess.abstracts.ProductDal;
import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public String productCodeCanNotBeDuplicatedWhenInserted(String productCode){

        boolean productCodeExists= productDal.existsByProductCode(productCode);

        if(productCodeExists){

            productCode = BarcodeGenerator.barcodeGenerator();
          return  productCodeCanNotBeDuplicatedWhenInserted(productCode);


        }
        else return  productCode;


    }
    public void validateSortDirection(String sortDirection) {

        String normalizedDirection = sortDirection.trim().toLowerCase();

        if (!normalizedDirection.equals("asc") && !normalizedDirection.equals("desc")) {

            throw new BusinessException("Invalid sort direction type! Please choose one of these directions: ASC, DESC");
        }
    }

    public void validateSortField(String field) {

        List<String> validFields = new ArrayList<>();
        Field[] fields=  Product.class.getDeclaredFields();

        for (Field f : fields) {

            if (!f.getName().equals("category")) {
                validFields.add(f.getName());
            }

        }
        validFields.add("categoryId");

        if (!validFields.contains(field)) {
            throw new BusinessException("Invalid sort field ! Please choose one of the following fields : "+ String.join(", ", validFields));
        }
    }

    public String mapFieldToDatabaseColumn(String field) {
        if (field.equals("price")) return "price.price";
        if (field.equals("stock")) return "stock.stockQuantity";
        return field;
    }
    public List<Product> paginationProcess(PageInfo pageInfo){

        if (pageInfo != null){

            PageRequest pageRequest = PageRequest.of(pageInfo.getPageIndex(), pageInfo.getPageSize());
            return productDal.findAll(pageRequest).getContent();

        }
        else  return productDal.findAll();
    }

}
