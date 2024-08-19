package com.haratres.SpringSecurity.business.dtos.address;

import com.haratres.SpringSecurity.entities.concretes.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAddressResponse {

    private int addressId;

    private  int userId;

    private String addressName;

    private String addressType;

    private  String street;

    private  String city;

    private  String district;

    private  String postalCode;

    private  String country;

    private  String addressLine;

    private  String phoneNumber ;
}
