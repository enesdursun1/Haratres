package com.haratres.SpringSecurity.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="addresses")
public class Address {

    @Column(name = "addressId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name = "addressName")
    private String addressName;

    @Column(name = "addressType")
    private String addressType;

    @Column(name = "street")
    private  String street;

    @Column(name = "city")
    private  String city;

    @Column(name = "district")
    private  String district;

    @Column(name = "postalCode")
    private  String postalCode;

    @Column(name = "country")
    private  String country;

    @Column(name = "addressLine")
    private  String addressLine;

    @Column(name = "phoneNumber")
    private  String phoneNumber ;

    public enum AddressType {
        HOME,
        OFFICE,
        OUTDOOR
    }

}
