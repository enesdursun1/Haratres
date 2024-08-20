package com.haratres.SpringSecurity.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="prices")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","product"})
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceId")
    private int priceId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @OneToOne()
    @JoinColumn(name="productId")
    private Product product;

    public Price(BigDecimal price, String currency, Product product) {
        this.price = price;
        this.currency = currency;
        this.product = product;
    }
}
