package com.aishwarya.ethical.transparency_portal.modules.product.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

	private String description;

	private String imageUrl;

	private String brand;

	private double ethicalScore;

	private double transparencyScore;

	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	
	@Transient
	private List<EthicalItem> ethicalSummary;
	
	@Transient
	private List<IngredientItem> ingredients;
	
	@Transient
	private TransparencyAnalysis transparencyAnalysis;

}