package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.CartProductService;
import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.dtos.cartProduct.*;
import com.haratres.SpringSecurity.business.dtos.product.GetByIdProductRequest;
import com.haratres.SpringSecurity.business.rules.CartProductBusinessRules;
import com.haratres.SpringSecurity.core.helpers.auth.AuthHelper;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartProductDal;
import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import com.haratres.SpringSecurity.entities.concretes.CustomUserDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductManager implements CartProductService {

    @Autowired
    private CartProductDal cartProductDal;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private CartProductBusinessRules cartProductBusinessRules;

    @Override
    public List<GetAllCartProductResponse> getAll() {
        List<CartProduct> cartProducts = cartProductDal.findAll();
        List<GetAllCartProductResponse> response = cartProducts.stream().map(
                cartProduct -> this.modelMapperService.forResponse().map(cartProduct, GetAllCartProductResponse.class)).toList();

        return response;
    }

    @Override
    public GetByIdCartProductResponse getById(GetByIdCartProductRequest getByIdCartProductRequest) {

        cartProductBusinessRules.cartProductShouldBeExistWhenSelected(getByIdCartProductRequest.getCartProductId());

        CartProduct cartProduct = cartProductDal.findById(getByIdCartProductRequest.getCartProductId()).get();
        GetByIdCartProductResponse response = this.modelMapperService.forResponse().map(cartProduct, GetByIdCartProductResponse.class);

        return response;
    }

    @Override
    public CreatedCartProductResponse add(CreateCartProductRequest createCartProductRequest) {

        createCartProductRequest.setUserId(AuthHelper.getuserId());

        CartProduct cartProduct = modelMapperService.forRequest().map(createCartProductRequest, CartProduct.class);

        setPrice(createCartProductRequest.getProductId(), cartProduct);

        CartProduct createdCartProduct = cartProductDal.save(cartProduct);

        CreatedCartProductResponse response = this.modelMapperService.forResponse().map(createdCartProduct, CreatedCartProductResponse.class);
        return response;


    }

    @Override
    public UpdatedCartProductResponse update(UpdateCartProductRequest updateCartProductRequest) {

        cartProductBusinessRules.cartProductShouldBeExistWhenSelected(updateCartProductRequest.getCartProductId());

        updateCartProductRequest.setUserId(AuthHelper.getuserId());

        CartProduct cartProduct = modelMapperService.forRequest().map(updateCartProductRequest, CartProduct.class);

        setPrice(updateCartProductRequest.getProductId(), cartProduct);

        CartProduct updatedCartProduct= cartProductDal.save(cartProduct);
        UpdatedCartProductResponse response = this.modelMapperService.forResponse().map(updatedCartProduct, UpdatedCartProductResponse.class);
        return response;
    }

    @Override
    public void delete(DeleteCartProductRequest deleteCartProductRequest) {

        cartProductBusinessRules.cartProductShouldBeExistWhenSelected(deleteCartProductRequest.getCartProductId());

        cartProductDal.deleteById(deleteCartProductRequest.getCartProductId());
    }

    @Override
    public CartProductResponse addOrUpdateCartProduct(CreateCartProductRequest createCartProductRequest) {

        createCartProductRequest.setUserId(AuthHelper.getuserId());
        if (!cartProductBusinessRules.checkProductInCart(createCartProductRequest.getProductId(),createCartProductRequest.getUserId()))
           return add(createCartProductRequest);

       else {
           CartProduct cartProduct = cartProductDal.findByProduct_ProductIdAndUser_UserId(createCartProductRequest.getProductId(),createCartProductRequest.getUserId());
           cartProduct.setQuantity(cartProduct.getQuantity() + createCartProductRequest.getQuantity());
           cartProduct.setTotalPrice();

           UpdateCartProductRequest updateCartProductRequest = modelMapperService.forRequest().map(cartProduct, UpdateCartProductRequest.class);
            return update(updateCartProductRequest);
        }
    }

    @Override
    public GetByUserIdCartProductWithTotalPriceResponse getListByUserId(GetByUserIdCartProductRequest getByUserIdCartProductRequest) {

        getByUserIdCartProductRequest.setUserId(AuthHelper.getuserId());

        cartProductBusinessRules.isCartEmptyForUser(getByUserIdCartProductRequest.getUserId());

        List<CartProduct> cartProducts = cartProductDal.findByUser_UserId(getByUserIdCartProductRequest.getUserId());

        List<GetByUserIdCartProductResponse> cartProductResponses = cartProducts.stream().map(
                cartProduct -> this.modelMapperService.forResponse().map(cartProduct, GetByUserIdCartProductResponse.class)).toList();

        GetByUserIdCartProductWithTotalPriceResponse reponse = new GetByUserIdCartProductWithTotalPriceResponse();
        reponse.setGetByUserIdCartProductResponseList(cartProductResponses);
        reponse.setTotalPrice();

          return reponse;
    }



    @Transactional
    @Override
    public void deleteByUserId() {

     cartProductBusinessRules.isCartEmptyForUser(AuthHelper.getuserId());

      DeleteByUserIdCartProductRequest deleteByUserIdCartProductRequest = new DeleteByUserIdCartProductRequest(AuthHelper.getuserId());
      cartProductDal.deleteByUser_UserId(deleteByUserIdCartProductRequest.getUserId());

    }



    private void setPrice(int productId,CartProduct cartProduct){

        GetByIdProductRequest getByIdProductRequest = new GetByIdProductRequest(productId);
        cartProduct.getProduct().setPrice(productService.getById(getByIdProductRequest).getPrice());
        cartProduct.setTotalPrice();

    }

}


