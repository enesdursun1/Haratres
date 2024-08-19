package com.haratres.SpringSecurity.business.abstracts;

import com.haratres.SpringSecurity.business.dtos.address.*;

import java.util.List;

public interface AddressService {

    List<GetAllAddressResponse> getAll();
    List<GetListByUserIdAddressResponse> getListByUserId();
    CreatedAddressResponse add(CreateAddressRequest createAddressRequest);
    UpdatedAddressResponse update(UpdateAddressRequest updateAddressRequest);
    void delete(DeleteAddressRequest deleteAddressRequest);

}
