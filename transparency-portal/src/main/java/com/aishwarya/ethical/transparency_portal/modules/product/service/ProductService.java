package com.aishwarya.ethical.transparency_portal.modules.product.service;

import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductCategoryDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
import com.aishwarya.ethical.transparency_portal.modules.product.model.EthicalItem;
import com.aishwarya.ethical.transparency_portal.modules.product.model.IngredientItem;
import com.aishwarya.ethical.transparency_portal.modules.product.model.TransparencyAnalysis;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ScoreBreakdown;
import com.aishwarya.ethical.transparency_portal.modules.product.model.EthicalItemEntity;
import com.aishwarya.ethical.transparency_portal.modules.product.model.IngredientItemEntity;
import com.aishwarya.ethical.transparency_portal.modules.product.model.TransparencyAnalysisEntity;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ScoreBreakdownEntity;

import java.util.stream.Collectors;
import com.aishwarya.ethical.transparency_portal.exception_handling.ProductNotFoundException;
//import org.springframework.security.access.prepost.PreAuthorize;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductModel;
import com.aishwarya.ethical.transparency_portal.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final ObjectMapper objectMapper;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.objectMapper = new ObjectMapper();
	}
	
	/**
	 * Returns all available product categories.
	 * @return List of ProductCategory
	 */
	public List<ProductCategory> getAllCategories() {
		return Arrays.asList(ProductCategory.values());
	}

	/**
	 * Returns all available product categories with their icons.
	 * @return List of ProductCategoryDTO
	 */
	public List<ProductCategoryDTO> getAllCategoriesWithIcons() {
        return ProductCategory.getAllWithIcons();
    }

	/**
	 * Returns all products for a given category.
	 * @param category Category name (case-insensitive)
	 * @return List of ProductDTO
	 */
	public List<ProductDTO> getProductsByCategory(String category) {
		if (category == null || category.trim().isEmpty()) {
			throw new IllegalArgumentException("Category must not be empty.");
		}
		ProductCategory cat;
		try {
			cat = ProductCategory.valueOf(category.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new ProductNotFoundException("Invalid category: " + category);
		}
		List<ProductModel> products = productRepository.findByCategory(cat);
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("No products found for category: " + category);
		}
		return products.stream().map(this::toDTO).collect(Collectors.toList());
	}

	/**
	 * Maps ProductModel to ProductDTO with dynamic data from database entities.
	 */
	private ProductDTO toDTO(ProductModel model) {
		if (model == null) return null;
		return new ProductDTO(
				model.getId(),
				model.getProductName(),
				model.getDescription(),
				model.getImageUrl(),
				model.getBrand(),
				model.getEthicalScore(),
				model.getTransparencyScore(),
				model.getCategory(),
				convertEthicalItemsEntityToDTO(model.getEthicalSummary()),
				convertIngredientsEntityToDTO(model.getIngredients()),
				convertTransparencyAnalysisEntityToDTO(model.getTransparencyAnalysis())
		);
	}
	
	/**
	 * Converts EthicalItemEntity list to EthicalItem list (DTO format).
	 */
	private List<EthicalItem> convertEthicalItemsEntityToDTO(List<EthicalItemEntity> entities) {
		if (entities == null || entities.isEmpty()) {
			return new java.util.ArrayList<>();
		}
		return entities.stream().map(entity -> {
			EthicalItem item = new EthicalItem();
			item.setTitle(entity.getTitle());
			item.setDescription(entity.getDescription());
			item.setIcon(entity.getIcon());
			return item;
		}).collect(Collectors.toList());
	}
	
	/**
	 * Converts IngredientItemEntity list to IngredientItem list (DTO format).
	 */
	private List<IngredientItem> convertIngredientsEntityToDTO(List<IngredientItemEntity> entities) {
		if (entities == null || entities.isEmpty()) {
			return new java.util.ArrayList<>();
		}
		return entities.stream().map(entity -> {
			IngredientItem item = new IngredientItem();
			item.setName(entity.getName());
			item.setDescription(entity.getDescription());
			item.setSafetyStatus(entity.getSafetyStatus());
			return item;
		}).collect(Collectors.toList());
	}
	
	/**
	 * Converts TransparencyAnalysisEntity to TransparencyAnalysis (DTO format).
	 */
	private TransparencyAnalysis convertTransparencyAnalysisEntityToDTO(TransparencyAnalysisEntity entity) {
		if (entity == null) {
			return null;
		}
		TransparencyAnalysis analysis = new TransparencyAnalysis();
		
		// Parse JSON strings back to lists
		try {
			if (entity.getScoreHighReasonsJson() != null) {
				List<String> reasons = objectMapper.readValue(
					entity.getScoreHighReasonsJson(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
				);
				analysis.setScoreHighReasons(reasons);
			}
			if (entity.getImprovementAreasJson() != null) {
				List<String> areas = objectMapper.readValue(
					entity.getImprovementAreasJson(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
				);
				analysis.setImprovementAreas(areas);
			}
		} catch (Exception e) {
			// If parsing fails, set empty lists
			analysis.setScoreHighReasons(new java.util.ArrayList<>());
			analysis.setImprovementAreas(new java.util.ArrayList<>());
		}
		
		// Convert ScoreBreakdownEntity to ScoreBreakdown
		if (entity.getScoreBreakdown() != null) {
			ScoreBreakdown breakdown = new ScoreBreakdown();
			breakdown.setIngredientTransparency(entity.getScoreBreakdown().getIngredientTransparency());
			breakdown.setEthicalCertifications(entity.getScoreBreakdown().getEthicalCertifications());
			breakdown.setManufacturingInfo(entity.getScoreBreakdown().getManufacturingInfo());
			breakdown.setSourcingTransparency(entity.getScoreBreakdown().getSourcingTransparency());
			analysis.setScoreBreakdown(breakdown);
		}
		
		return analysis;
	}



	/**
	 * Returns all products in the database.
	 * 
	 * @return List of ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public List<ProductDTO> getAllProducts() {
		List<ProductModel> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found.");
		}
		return products.stream().map(this::toDTO).collect(Collectors.toList());
	}

	/**
	 * Searches for products by product name (case-insensitive, partial match).
	 * 
	 * @param name Product name or part of it
	 * @return List of ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public List<ProductDTO> searchProductsByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be empty.");
		}
		List<ProductModel> products = productRepository.findByProductNameContainingIgnoreCase(name);
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("No products found matching: " + name);
		}
		return products.stream().map(this::toDTO).collect(Collectors.toList());
	}

	/**
	 * Gets a product by its ID.
	 * 
	 * @param id Product ID
	 * @return ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public ProductDTO getProductById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Product ID must not be null.");
		}
		ProductModel model = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		return toDTO(model);
	}

}