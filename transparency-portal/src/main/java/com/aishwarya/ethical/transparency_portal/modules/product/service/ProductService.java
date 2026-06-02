package com.aishwarya.ethical.transparency_portal.modules.product.service;

import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductCategoryDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
import com.aishwarya.ethical.transparency_portal.modules.product.model.EthicalItem;
import com.aishwarya.ethical.transparency_portal.modules.product.model.IngredientItem;
import com.aishwarya.ethical.transparency_portal.modules.product.model.TransparencyAnalysis;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ScoreBreakdown;

import java.util.stream.Collectors;
import com.aishwarya.ethical.transparency_portal.exception_handling.ProductNotFoundException;
//import org.springframework.security.access.prepost.PreAuthorize;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductModel;
import com.aishwarya.ethical.transparency_portal.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
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
	 * Maps ProductModel to ProductDTO with static mock data for extended details.
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
				getMockEthicalItems(),
				getMockIngredients(),
				getMockTransparencyAnalysis()
		);
	}

	/**
	 * Returns mock ethical items for product details page.
	 */
	private List<EthicalItem> getMockEthicalItems() {
		EthicalItem item1 = new EthicalItem();
		item1.setTitle("No Animal Testing");
		item1.setDescription("Certified cruelty-free by Leaping Bunny");
		item1.setIcon("fheart-icon");

		EthicalItem item2 = new EthicalItem();
		item2.setTitle("95% Vegan");
		item2.setDescription("Contains trace amounts of beeswax, but otherwise plant-based");
		item2.setIcon("tick-icon");

		EthicalItem item3 = new EthicalItem();
		item3.setTitle("Low Risk Level");
		item3.setDescription("Contains 1 ingredient flagged for caution (Parfum), but overall low risk");
		item3.setIcon("secure-icon");
		
		EthicalItem item4 = new EthicalItem();
		item3.setTitle("Contains Fragrance");
		item3.setDescription("May cause irritation in sensitive individuals");
		item3.setIcon("warning-icon");

		return Arrays.asList(item1, item2, item4);
	}

	/**
	 * Returns mock ingredients for product details page.
	 */
	private List<IngredientItem> getMockIngredients() {
		IngredientItem ingredient1 = new IngredientItem();
		ingredient1.setName("Parfum (Fragrance)");
		ingredient1.setDescription("May cause allergic reactions");														
		ingredient1.setSafetyStatus("Warning");

		IngredientItem ingredient2 = new IngredientItem();
		ingredient2.setName("Retinol");
		ingredient2.setDescription("Powerful but can irritate sensitive skin, especially in high concentrations");
		ingredient2.setSafetyStatus("Caution");

		IngredientItem ingredient3 = new IngredientItem();
		ingredient3.setName("Parabens");
		ingredient3.setDescription("Preservative with hormone-disrupting concerns");
		ingredient3.setSafetyStatus("Harmful");
		
		IngredientItem ingredient4 = new IngredientItem();
		ingredient4.setName("Aqua (Water)");
		ingredient4.setDescription("Base ingredient");
		ingredient4.setSafetyStatus("Safe");

		IngredientItem ingredient5 = new IngredientItem();
		ingredient5.setName("Glycerin");
		ingredient5.setDescription("Moisturizing agent that helps retain skin hydration");
		ingredient5.setSafetyStatus("Safe");

		IngredientItem ingredient6 = new IngredientItem();
		ingredient6.setName("Niacinamide");
		ingredient6.setDescription("Vitamin B3, brightening");
		ingredient6.setSafetyStatus("Safe");

		return Arrays.asList(ingredient4, ingredient5, ingredient6, ingredient1, ingredient2, ingredient3);
	}

	/**
	 * Returns mock transparency analysis for product details page.
	 */
	private TransparencyAnalysis getMockTransparencyAnalysis() {
		ScoreBreakdown breakdown = new ScoreBreakdown();
		breakdown.setIngredientTransparency(95);
		breakdown.setEthicalCertifications(90);
		breakdown.setManufacturingInfo(85);
		breakdown.setSourcingTransparency(80);

		TransparencyAnalysis analysis = new TransparencyAnalysis();
		analysis.setScoreHighReasons(Arrays.asList(
			"Complete ingredient list with INCI names provided",
			"Third-party certifications verified (Leaping Bunny, EWG)",
			"Manufacturing location and process disclosed",
			"Sustainability practices clearly documented"
		));
		analysis.setImprovementAreas(Arrays.asList(
			"Contains \"Parfum (Fragrance)\" - a vague ingredient that may hide allergens",
			"Parabens present - considered controversial by some health organizations"
		));
		analysis.setScoreBreakdown(breakdown);

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