package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.CartProductService;
import com.haratres.SpringSecurity.business.abstracts.CartService;
import com.haratres.SpringSecurity.business.dtos.cart.*;
import com.haratres.SpringSecurity.business.dtos.cartProduct.GetListByCartIdCartProductResponse;
import com.haratres.SpringSecurity.business.rules.CartBusinessRules;
import com.haratres.SpringSecurity.core.helpers.auth.AuthHelper;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartDal;
import com.haratres.SpringSecurity.dataAccess.abstracts.ProductDal;
import com.haratres.SpringSecurity.entities.concretes.Cart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartManager implements CartService {

    @Autowired
    private CartDal cartDal;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private CartProductService cartProductService;
    @Autowired
    private CartBusinessRules cartBusinessRules;


    @Override
    public List<GetAllCartResponse> getAll() {

        List<Cart> carts = cartDal.findAll();

        List<GetAllCartResponse> response = carts.stream().map(cart ->
                this.modelMapperService.forResponse().map(carts,GetAllCartResponse.class)).toList();

        return response;
    }

    @Override
    public GetByIdCartResponse getById(GetByIdCartRequest getByIdCartRequest) {

        Cart cart = cartDal.findById(getByIdCartRequest.getCartId()).get();
        GetByIdCartResponse response = this.modelMapperService.forResponse().map(cart,GetByIdCartResponse.class);
        return response;


    }

    @Override
    public CreatedCartResponse add(CreateCartRequest createCartRequest) {

        Cart cart = this.modelMapperService.forRequest().map(createCartRequest,Cart.class);

        Cart createdCart = cartDal.save(cart);

        CreatedCartResponse response = this.modelMapperService.forResponse().map(createdCart,CreatedCartResponse.class);

        return response;
    }

    @Override
    public UpdatedCartResponse update(UpdateCartRequest updateCartRequest) {

        Cart cart = this.modelMapperService.forRequest().map(updateCartRequest,Cart.class);

        Cart updatedCart = cartDal.save(cart);

        UpdatedCartResponse response = this.modelMapperService.forResponse().map(updatedCart,UpdatedCartResponse.class);

        return response;
    }

    @Override
    public void delete(DeleteCartRequest deleteCartRequest) {

        cartDal.deleteById(deleteCartRequest.getCartId());
    }

    @Override
    public GetListByUserIdCartResponse getListByUserId() {

        int userId = AuthHelper.getuserId();
        Cart cart = cartDal.getByUser_UserId(userId);

        cartBusinessRules.cartShouldBeExistsWhenSelected(userId);

      List<GetListByCartIdCartProductResponse> getListByCartIdCartProductResponse =
              cartProductService.getListByCartId(cart.getCartId());

     GetListByUserIdCartResponse response = new GetListByUserIdCartResponse(getListByCartIdCartProductResponse);
     response.setTotalPrice(cart.getTotalPrice());

        return response;

    }

    @Transactional
    @Override
    public void clearCartProducts() {

        int userId = AuthHelper.getuserId();

        cartBusinessRules.cartShouldBeExistsWhenSelected(userId);

        Cart cart = cartDal.getByUser_UserId(userId);
        cartProductService.deleteByCartId(cart.getCartId());
        cartDal.deleteByUser_UserId(userId);

    }
}
