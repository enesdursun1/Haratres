package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.AddressService;
import com.haratres.SpringSecurity.business.dtos.address.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {


    @Autowired
    private AddressService addressService;


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllAddressResponse> getAll() {

        return addressService.getAll();

    }

    @GetMapping("/getListByUserId")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListByUserIdAddressResponse> getListByUserId() {

        return addressService.getListByUserId();

    }

    @PostMapping("/add")
    public CreatedAddressResponse add(@RequestBody @Valid CreateAddressRequest createAddressRequest) {

        return  addressService.add(createAddressRequest);
    }


    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAddressResponse update(@RequestBody @Valid UpdateAddressRequest updateAddressRequest) {

        return addressService.update(updateAddressRequest);

    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid  DeleteAddressRequest deleteAddressRequest) {

        addressService.delete(deleteAddressRequest);

    }
}
