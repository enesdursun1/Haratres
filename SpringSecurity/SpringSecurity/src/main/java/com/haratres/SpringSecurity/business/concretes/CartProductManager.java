package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.CartProductService;
import com.haratres.SpringSecurity.business.abstracts.CartService;
import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.abstracts.UserService;
import com.haratres.SpringSecurity.business.dtos.cart.UpdateCartRequest;
import com.haratres.SpringSecurity.business.dtos.cartProduct.*;
import com.haratres.SpringSecurity.business.dtos.product.GetByIdProductRequest;
import com.haratres.SpringSecurity.business.dtos.product.GetByIdProductResponse;
import com.haratres.SpringSecurity.business.rules.CartProductBusinessRules;
import com.haratres.SpringSecurity.core.helpers.auth.AuthHelper;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartDal;
import com.haratres.SpringSecurity.dataAccess.abstracts.CartProductDal;
import com.haratres.SpringSecurity.entities.concretes.Cart;
import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import com.haratres.SpringSecurity.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartProductManager implements CartProductService {

    @Autowired
    private CartProductDal cartProductDal;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private CartProductBusinessRules cartProductBusinessRules;
    @Autowired
    private CartDal cartDal;


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

        CartProduct cartProduct = modelMapperService.forRequest().map(createCartProductRequest, CartProduct.class);

        User user =  findUser();

        setCartWhenInserted(cartProduct,user);
        setPrice(createCartProductRequest.getProductId(), cartProduct);




        CartProduct createdCartProduct = cartProductDal.save(cartProduct);

        cartTotalPriceUpdate();

        CreatedCartProductResponse response = this.modelMapperService.forResponse().map(createdCartProduct, CreatedCartProductResponse.class);
        return response;


    }
    @Override
   public UpdatedCartProductResponse update(UpdateCartProductRequest updateCartProductRequest) {

        cartProductBusinessRules.cartProductShouldBeExistWhenSelected(updateCartProductRequest.getCartProductId());

        CartProduct cartProduct = modelMapperService.forRequest().map(updateCartProductRequest, CartProduct.class);

        setPrice(updateCartProductRequest.getProductId(), cartProduct);
        setCartWhenUpdated(cartProduct);

        CartProduct updatedCartProduct= cartProductDal.save(cartProduct);

        cartTotalPriceUpdate();

        UpdatedCartProductResponse response = this.modelMapperService.forResponse().map(updatedCartProduct, UpdatedCartProductResponse.class);

        return response;
    }

   @Override
    public void delete(DeleteCartProductRequest deleteCartProductRequest) {

        cartProductBusinessRules.cartProductShouldBeExistWhenSelected(deleteCartProductRequest.getCartProductId());

        cartProductDal.deleteById(deleteCartProductRequest.getCartProductId());

        cartTotalPriceUpdate();
    }

    @Override
    public CartProductResponse addOrUpdateCartProduct(CreateCartProductRequest createCartProductRequest) {

        int userId = AuthHelper.getuserId();

        if (!cartProductBusinessRules.checkProductInCart(createCartProductRequest.getProductId(),userId))
           return add(createCartProductRequest);

       else {

           CartProduct cartProduct = findByProductIdAndUserId(createCartProductRequest.getProductId(),userId);
           cartProduct.setQuantity(cartProduct.getQuantity() + createCartProductRequest.getQuantity());
           cartProduct.setTotalPrice();

           UpdateCartProductRequest updateCartProductRequest = modelMapperService.forRequest().map(cartProduct, UpdateCartProductRequest.class);
            return update(updateCartProductRequest);
        }
    }

    @Override
    public List<GetListByCartIdCartProductResponse> getListByCartId(int cartId) {

        List<CartProduct> cartProducts = cartProductDal.findByCart_CartId(cartId);

        List<GetListByCartIdCartProductResponse> cartProductResponses = cartProducts.stream().map(
                cartProduct -> this.modelMapperService.forResponse().map(cartProduct, GetListByCartIdCartProductResponse.class)).toList();

        return cartProductResponses;
    }


    @Override
    public void deleteByCartId(int cartId) {


      cartProductDal.deleteByCart_CartId(cartId);

    }



    private void setPrice(int productId,CartProduct cartProduct){

        GetByIdProductRequest getByIdProductRequest = new GetByIdProductRequest(productId);
        GetByIdProductResponse response = productService.getById(getByIdProductRequest);

        cartProduct.setProduct(response.getPrice().getProduct());
        cartProduct.getProduct().getPrice().setPrice(response.getPrice().getPrice());
        cartProduct.setTotalPrice();



    }

    private void setCartWhenInserted(CartProduct cartProduct,User user){

        Cart cart =  cartDal.getByUser_UserId(user.getUserId());

        if(cart==null) {

            Cart newCart= new Cart(user);
            cartProduct.setCart(newCart);
            cartDal.save(newCart);

        }


        else cartProduct.setCart(cart);




    }
     private void setCartWhenUpdated(CartProduct cartProduct){

         cartProduct.setCart(cartDal.getByUser_UserId(findUser().getUserId()));


    }

    private User findUser(){

        return userService.getById(AuthHelper.getuserId());


    }
    private CartProduct findByProductIdAndUserId(int productId,int userId){

      Cart cart = cartDal.getByUser_UserId(userId);
      return  cartProductDal.findByProduct_ProductIdAndCart_CartId(productId,cart.getCartId());


    }


    public void cartTotalPriceUpdate() {

        Cart cart = cartDal.getByUser_UserId(findUser().getUserId());

        List<GetListByCartIdCartProductResponse> response = getListByCartId(cart.getCartId());

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (GetListByCartIdCartProductResponse getListByCartIdCartProductResponse : response) {
            totalPrice = totalPrice.add(getListByCartIdCartProductResponse.getTotalPrice());
        }
        cart.setTotalPrice(totalPrice);

        cartDal.save(cart);

    }

}


