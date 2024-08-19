package com.haratres.SpringSecurity.business.dtos.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAddressResponse {

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
