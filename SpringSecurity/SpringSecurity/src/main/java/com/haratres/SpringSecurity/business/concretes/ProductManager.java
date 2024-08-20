package com.haratres.SpringSecurity.business.concretes;

import java.util.List;
import java.util.Optional;

import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.dtos.product.*;
import com.haratres.SpringSecurity.business.rules.ProductBusinessRules;
import com.haratres.SpringSecurity.business.utilities.BarcodeGenerator;
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

		Optional<Product> productCheck = productDal.findById(getByIdProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);

		Product product = productDal.findById(getByIdProductRequest.getProductId()).get();
		GetByIdProductResponse response = this.modelMapperService.forResponse().map(product, GetByIdProductResponse.class);

		return response;
	}

	@Override
	public CreatedProductResponse add(CreateProductRequest createProductRequest) {

		productBusinessRules.productNameCanNotBeDuplicatedWhenInserted(createProductRequest.getProductName());
        productBusinessRules.categoryShouldBeExistWhenSelected(createProductRequest.getCategoryId());

		String productCode = BarcodeGenerator.barcodeGenerator();

		productCode = productBusinessRules.productCodeCanNotBeDuplicatedWhenInserted(productCode);

		createProductRequest.setProductCode(productCode);

		Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);

		product.setProductId(0); //categoryId parametresini productId olarak aldığı için 0 değeri atandı.

		Product createdProduct = productDal.save(product);
		CreatedProductResponse response = this.modelMapperService.forResponse().map(createdProduct, CreatedProductResponse.class);

		return response;
	}

	@Override
	public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

		Optional<Product> productCheck = productDal.findById(updateProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);
		productBusinessRules.productNameCanNotBeDuplicatedWhenUpdated(updateProductRequest.getProductName(), updateProductRequest.getProductId());
		productBusinessRules.categoryShouldBeExistWhenSelected(updateProductRequest.getCategoryId());

        updateProductRequest.setProductCode(productCheck.get().getProductCode());

		Product product = modelMapperService.forRequest().map(updateProductRequest, Product.class);
		Product updatedProduct = productDal.save(product);
		UpdatedProductResponse response = this.modelMapperService.forResponse().map(updatedProduct, UpdatedProductResponse.class);
		return response;
	}


	@Override
	public void delete(DeleteProductRequest deleteProductRequest) {

		Optional<Product> productCheck = productDal.findById(deleteProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);

        productDal.deleteById(deleteProductRequest.getProductId());
	}

	@Override
	public GetByNameOrCodeProductResponse getByNameOrCode(String keyword) {

		keyword = keyword.toLowerCase();

		Product product = productDal.findByProductNameOrProductCode(keyword);

		productBusinessRules.productShouldBeExistWhenSearch(product);

        GetByNameOrCodeProductResponse response = this.modelMapperService.forResponse().map(product, GetByNameOrCodeProductResponse.class);

		return response;
	}


}
