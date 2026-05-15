package com.aishwarya.ethical.transparency_portal.modules.product.service;

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
	 * Returns all products in the database.
	 * 
	 * @return List of ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public List<ProductModel> getAllProducts() {
		List<ProductModel> products = productRepository.findAll();
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("No products found.");
		}
		return products;
	}

	/**
	 * Searches for products by product name (case-insensitive, partial match).
	 * 
	 * @param name Product name or part of it
	 * @return List of ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public List<ProductModel> searchProductsByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be empty.");
		}
		List<ProductModel> products = productRepository.findByProductNameContainingIgnoreCase(name);
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("No products found matching: " + name);
		}
		return products;
	}

	/**
	 * Gets a product by its ID.
	 * 
	 * @param id Product ID
	 * @return ProductModel
	 */
	// @PreAuthorize("isAuthenticated()")
	public ProductModel getProductById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Product ID must not be null.");
		}
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
	}

}