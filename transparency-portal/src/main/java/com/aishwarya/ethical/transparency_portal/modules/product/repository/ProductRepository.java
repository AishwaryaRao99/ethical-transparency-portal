package com.aishwarya.ethical.transparency_portal.modules.product.repository;

import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    // Find products by name containing (case-insensitive)
    List<ProductModel> findByProductNameContainingIgnoreCase(String name);

    // Find products by category
    List<ProductModel> findByCategory(ProductCategory category);
}