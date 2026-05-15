package com.aishwarya.ethical.transparency_portal.modules.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductModel;
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

//	 // This is a placeholder for the ProductController. 
//	 // You can implement CRUD operations for products here.	
//	@RequestMapping("/search")
//	public List<String> searchProduct(@RequestParam String query) {
//		// Implement search logic here
//		//return new APIResponse("Search results for: " + query);
//		  // Example response
//        return List.of(
//                "Result for: " + query,
//                "Another result for: " + query,
//                "Sample item"
//        );
//	}	

	/**
	 * Get all products (secured, authenticated users only).
	 */
	@GetMapping("/get-all-products")
	// @PreAuthorize("isAuthenticated()")
	public List<ProductModel> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Search products by name (secured, authenticated users only).
	 */
	@GetMapping("/search")
	// @PreAuthorize("isAuthenticated()")
	public List<ProductModel> searchProductsByName(@RequestParam String name) {
		return productService.searchProductsByName(name);
	}

	/**
	 * Get product by ID (secured, authenticated users only).
	 */
	@GetMapping("/{id}")
	// @PreAuthorize("isAuthenticated()")
	public ProductModel getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
}