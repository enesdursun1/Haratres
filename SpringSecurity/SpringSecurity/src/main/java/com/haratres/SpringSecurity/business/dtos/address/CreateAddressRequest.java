package com.haratres.SpringSecurity.business.dtos.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {

    private  int userId;

    @NotBlank
    @NotNull
    private String addressName;

    @NotBlank
    @NotNull
    private String addressType;

    @NotBlank
    @NotNull
    private  String street;

    @NotBlank
    @NotNull
    private  String city;

    @NotBlank
    @NotNull
    private  String district;

    @NotBlank
    @NotNull
    private  String postalCode;

    @NotBlank
    @NotNull
    private  String country;

    @NotBlank
    @NotNull
    private  String addressLine;

    @NotBlank
    @NotNull
    private  String phoneNumber ;
}
