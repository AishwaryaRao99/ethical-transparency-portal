package com.aishwarya.ethical.transparency_portal.modules.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aishwarya.ethical.transparency_portal.common.APIResponse;

@RestController
@RequestMapping("/api/v1/productsapi")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
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
}
