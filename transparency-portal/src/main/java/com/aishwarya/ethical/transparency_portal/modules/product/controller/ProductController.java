package com.aishwarya.ethical.transparency_portal.modules.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductCategoryDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductDTO;
import com.aishwarya.ethical.transparency_portal.modules.product.service.ProductService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1/productsapi")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}


	/**
	 * Get all product categories for UI Home screen, with their icons.
	 * @return List of ProductCategoryDTO
	 */
	@GetMapping("/categories")
	public List<ProductCategoryDTO> getAllCategories() {
		return productService.getAllCategoriesWithIcons();
	}

	/**
	 * Get all products by category (DTO, secure, for UI category click).
	 */
	@GetMapping("/by-category")
	public List<ProductDTO> getProductsByCategory(@RequestParam String category) {
		return productService.getProductsByCategory(category);
	}

	/**
	 * Get all products (DTO, secure, authenticated users only).
	 */
	@GetMapping("/get-all-products")
	// @PreAuthorize("isAuthenticated()")
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Search products by name (DTO, secure, authenticated users only).
	 */
	@GetMapping("/search")
	// @PreAuthorize("isAuthenticated()")
	public List<ProductDTO> searchProductsByName(@RequestParam String name) {
		return productService.searchProductsByName(name);
	}

	/**
	 * Get product by ID (DTO, secure, authenticated users only).
	 */
	@GetMapping("/{id}")
	// @PreAuthorize("isAuthenticated()")
	public ProductDTO getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
}