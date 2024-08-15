package com.haratres.SpringSecurity.business.concretes;

import java.util.List;

import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.dtos.product.*;
import com.haratres.SpringSecurity.business.rules.ProductBusinessRules;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.dataAccess.abstracts.ProductDal;

@Service
public class ProductManager implements ProductService {

	@Autowired
	private ProductDal productDal;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private ProductBusinessRules productBusinessRules;


	@Override
	public List<GetAllProductResponse> getAll() {

		List<Product> products = productDal.findAll();
		List<GetAllProductResponse> response = products.stream().map(
				product -> this.modelMapperService.forResponse().map(product, GetAllProductResponse.class)).toList();

		return response;
	}

	@Override
	public GetByIdProductResponse getById(GetByIdProductRequest getByIdProductRequest) {

		productBusinessRules.productShouldBeExistWhenSelected(getByIdProductRequest.getProductId());

		Product product = productDal.findById(getByIdProductRequest.getProductId()).get();
		GetByIdProductResponse response = this.modelMapperService.forResponse().map(product, GetByIdProductResponse.class);

		return response;
	}

	@Override
	public CreatedProductResponse add(CreateProductRequest createProductRequest) {

		productBusinessRules.productNameCanNotBeDuplicatedWhenInserted(createProductRequest.getProductName());
        productBusinessRules.categoryShouldBeExistWhenSelected(createProductRequest.getCategoryId());

		Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);
		product.setProductId(0); //categoryId parametresini productId olarak aldığı için 0 değeri atandı.
		Product createdProduct = productDal.save(product);
		CreatedProductResponse response = this.modelMapperService.forResponse().map(createdProduct, CreatedProductResponse.class);

		return response;
	}

	@Override
	public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

		productBusinessRules.productShouldBeExistWhenSelected(updateProductRequest.getProductId());
		productBusinessRules.productNameCanNotBeDuplicatedWhenUpdated(updateProductRequest.getProductName(), updateProductRequest.getProductId());
		productBusinessRules.categoryShouldBeExistWhenSelected(updateProductRequest.getCategoryId());

		Product product = modelMapperService.forRequest().map(updateProductRequest, Product.class);
		Product updatedProduct = productDal.save(product);
		UpdatedProductResponse response = this.modelMapperService.forResponse().map(updatedProduct, UpdatedProductResponse.class);
		return response;
	}


	@Override
	public void delete(DeleteProductRequest deleteProductRequest) {

		productBusinessRules.productShouldBeExistWhenSelected(deleteProductRequest.getProductId());

        productDal.deleteById(deleteProductRequest.getProductId());
	}
}
