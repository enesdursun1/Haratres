package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.AddressService;
import com.haratres.SpringSecurity.business.dtos.address.*;
import com.haratres.SpringSecurity.business.rules.AddressBusinessRules;
import com.haratres.SpringSecurity.core.helpers.auth.AuthHelper;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.AddressDal;
import com.haratres.SpringSecurity.entities.concretes.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressManager implements AddressService {

    @Autowired
    private AddressDal addressDal;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private AddressBusinessRules addressBusinessRules;


    @Override
    public List<GetAllAddressResponse> getAll() {

        List<Address> addresses = addressDal.findAll();
        List<GetAllAddressResponse> response = addresses.stream().map(address ->
                this.modelMapperService.forResponse().map(address, GetAllAddressResponse.class)).toList();

        return response;
    }

    @Override
    public List<GetListByUserIdAddressResponse> getListByUserId() {

        List<Address> addresses = addressDal.findAllByUser_UserId(AuthHelper.getuserId());
        List<GetListByUserIdAddressResponse> response = addresses.stream().map(address ->
                this.modelMapperService.forResponse().map(address, GetListByUserIdAddressResponse.class)).toList();

        return response;


    }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {

        int userId=AuthHelper.getuserId();

        addressBusinessRules.validateAddressType(createAddressRequest.getAddressType());
        addressBusinessRules.addressNameCanNotBeDuplicatedWhenInserted(createAddressRequest.getAddressName(),userId);

        createAddressRequest.setUserId(userId);

        Address address = modelMapperService.forRequest().map(createAddressRequest, Address.class);
        Address createdAddress = addressDal.save(address);
        CreatedAddressResponse createdAddressResponse = modelMapperService.forResponse().map(createdAddress, CreatedAddressResponse.class);

        return createdAddressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest updateAddressRequest) {

        int userId=AuthHelper.getuserId();

        addressBusinessRules.validateAddressType(updateAddressRequest.getAddressType());
        addressBusinessRules.addressNameCanNotBeDuplicatedWhenUpdated(updateAddressRequest.getAddressName(), updateAddressRequest.getAddressId(), userId);

        updateAddressRequest.setUserId(userId);

        Address address = modelMapperService.forRequest().map(updateAddressRequest, Address.class);
        Address updatedAddress = addressDal.save(address);
        UpdatedAddressResponse updatedAddressResponse = modelMapperService.forResponse().map(updatedAddress, UpdatedAddressResponse.class);
        return updatedAddressResponse;

    }

    @Override
    public void delete(DeleteAddressRequest deleteAddressRequest) {

        addressBusinessRules.addressShouldBeExistWhenSelected(deleteAddressRequest.getAddressId());

        addressDal.deleteById(deleteAddressRequest.getAddressId());
    }
}
