package com.haratres.SpringSecurity.entities.concretes;


import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", indexes = {
		@Index(name = "idx_product_name", columnList = "productName"),
		@Index(name = "idx_product_code", columnList = "productCode")
})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
	private int productId;
	
	@Column(name="productName")
	private String productName;

	@Column(name="productCode")
	private String productCode;
	
	/*@Column(name="stock")
	private int stock;*/
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;

	
	@ManyToOne()
	@JoinColumn(name="categoryId")
	private Category category;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Price price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Stock stock;


}
