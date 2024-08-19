package com.haratres.SpringSecurity.business.dtos.address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {

    @Min(value = 1)
    @NotNull
    @Positive
    private int addressId;

    private  int userId;

    @NotNull
    @NotBlank
    private String addressName;

    @NotNull
    @NotBlank
    private String addressType;

    @NotNull
    @NotBlank
    private  String street;

    @NotNull
    @NotBlank
    private  String city;

    @NotNull
    @NotBlank
    private  String district;

    @NotNull
    @NotBlank
    private  String postalCode;

    @NotNull
    @NotBlank
    private  String country;

    @NotNull
    @NotBlank
    private  String addressLine;

    @NotNull
    @NotBlank
    private  String phoneNumber ;
}
