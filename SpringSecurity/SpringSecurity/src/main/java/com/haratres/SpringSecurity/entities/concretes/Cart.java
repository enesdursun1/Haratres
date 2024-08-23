package com.haratres.SpringSecurity.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProductList;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;



    public Cart(User user) {

        this.user= user ;
    }



}