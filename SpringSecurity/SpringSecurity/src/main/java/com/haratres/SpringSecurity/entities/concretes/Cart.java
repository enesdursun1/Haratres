package com.haratres.SpringSecurity.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    public Cart(User user) {

        this.user= user ;
    }



}