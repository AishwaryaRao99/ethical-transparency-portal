package com.aishwarya.ethical.transparency_portal.modules.product.service;

import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
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
	 * Maps ProductModel to ProductDTO.
	 */
	private ProductDTO toDTO(ProductModel model) {
		if (model == null) return null;
		return new ProductDTO(
				model.getId(),
				model.getProductName(),
				model.getDescription(),
				model.getImageUrl(),
				model.getEthicalScore(),
				model.getTransparencyScore(),
				model.getCategory()
		);
	}

	/**
	 * Maps ProductDTO to ProductModel.
	 */
	private ProductModel toModel(ProductDTO dto) {
		if (dto == null) return null;
		return new ProductModel(
				dto.getId(),
				dto.getProductName(),
				dto.getDescription(),
				dto.getImageUrl(),
				dto.getEthicalScore(),
				dto.getTransparencyScore(),
				dto.getCategory()
		);
	}

	/**
	 * Returns all products in the database.
	 * 
	 * @return List of ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public List<ProductDTO> getAllProducts() {
		List<ProductModel> products = productRepository.findAll();
		if (products == null || products.isEmpty()) {
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