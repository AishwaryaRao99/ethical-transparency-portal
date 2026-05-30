package com.aishwarya.ethical.transparency_portal.modules.product.dto;

import com.aishwarya.ethical.transparency_portal.modules.product.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private String imageUrl;
    private String brand;
    private double ethicalScore;
    private double transparencyScore;
    private ProductCategory category;
}
