package com.haratres.SpringSecurity.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cartproducts")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartProductId")
    int cartProductId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;




    public void setTotalPrice() {

       this.totalPrice = product.getPrice().multiply(new BigDecimal(quantity));
    }


}
