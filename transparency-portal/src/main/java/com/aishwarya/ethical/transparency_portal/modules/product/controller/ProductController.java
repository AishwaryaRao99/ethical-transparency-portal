package com.aishwarya.ethical.transparency_portal.modules.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aishwarya.ethical.transparency_portal.common.APIResponse;
import com.aishwarya.ethical.transparency_portal.modules.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/productsapi")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	 // This is a placeholder for the ProductController. 
	 // You can implement CRUD operations for products here.	
	@RequestMapping("/search")
	public List<String> searchProduct(@RequestParam String query) {
		// Implement search logic here
		//return new APIResponse("Search results for: " + query);
		  // Example response
        return List.of(
                "Result for: " + query,
                "Another result for: " + query,
                "Sample item"
        );
	}	
	
	@PostMapping("/add-dummy")
	@ResponseBody
	public APIResponse addDummyProducts() {
		productService.addDummyProducts();
		return new APIResponse("Dummy products added successfully.");
	}
}