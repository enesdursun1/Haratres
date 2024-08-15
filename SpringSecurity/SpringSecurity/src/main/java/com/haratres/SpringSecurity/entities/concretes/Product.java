package com.haratres.SpringSecurity.entities.concretes;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
	private int productId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	
	@ManyToOne()
	@JoinColumn(name="categoryId")
	private Category category;
	
}
