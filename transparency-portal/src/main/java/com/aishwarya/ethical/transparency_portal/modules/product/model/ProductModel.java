package com.aishwarya.ethical.transparency_portal.modules.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productName;

	@Column(length = 2000)
	private String description;

	private String imageUrl;

	private double ethicalScore;

	private double transparencyScore;

	   @Enumerated(EnumType.STRING)
	   private ProductCategory category;

}