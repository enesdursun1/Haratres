package com.haratres.SpringSecurity.business.abstracts;

import com.haratres.SpringSecurity.business.dtos.cartProduct.*;

import java.util.List;

public interface CartProductService {

    List<GetAllCartProductResponse> getAll();
    GetByIdCartProductResponse getById(GetByIdCartProductRequest getByIdCartProductRequest);
    CreatedCartProductResponse add(CreateCartProductRequest createCartProductRequest);
    UpdatedCartProductResponse update(UpdateCartProductRequest updateCartProductRequest);
    void delete(DeleteCartProductRequest deleteCartProductRequest);
    CartProductResponse addOrUpdateCartProduct(CreateCartProductRequest createCartProductRequest);
    List<GetListByCartIdCartProductResponse> getListByCartId(int cartId);
    void deleteByCartId(int cartId);

}
