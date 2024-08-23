package com.haratres.SpringSecurity.business.abstracts;

import com.haratres.SpringSecurity.business.dtos.cart.*;
import com.haratres.SpringSecurity.business.dtos.cartProduct.*;

import java.util.List;

public interface CartService {

    List<GetAllCartResponse> getAll();
    GetByIdCartResponse getById(GetByIdCartRequest getByIdCartRequest);
    CreatedCartResponse add(CreateCartRequest createCartRequest);
    UpdatedCartResponse update(UpdateCartRequest updateCartRequest);
    void delete(DeleteCartRequest deleteCartRequest);


    GetListByUserIdCartResponse getListByUserId();
    void clearCartProducts();


}
