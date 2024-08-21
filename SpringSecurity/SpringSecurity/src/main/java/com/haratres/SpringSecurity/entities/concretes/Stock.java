package com.haratres.SpringSecurity.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","product"})
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId")
    private int stockId;

    @OneToOne()
    @JoinColumn(name="productId")
    private Product product;

    @Column(name = "stockQuantity")
    private int stockQuantity;

    public Stock(int stockQuantity,Product product) {
        this.stockQuantity = stockQuantity;
        this.product = product;
    }
}
