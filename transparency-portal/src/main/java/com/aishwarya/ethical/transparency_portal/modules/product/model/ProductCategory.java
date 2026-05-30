package com.aishwarya.ethical.transparency_portal.modules.product.model;

import java.util.ArrayList;
import java.util.List;
import com.aishwarya.ethical.transparency_portal.modules.product.dto.ProductCategoryDTO;
/**
 * Enum representing product categories, each with an associated icon.
 */
public enum ProductCategory {
    FOOD("\uD83C\uDF72"),         // 🍲
    SKINCARE("\uD83D\uDC8E"),    // 💎
    CLEANING("\uD83D\uDEBF"),    // 🚿
    FASHION("\uD83D\uDC57");     // 👗
    // Add more categories as needed in the future

    private final String icon;

    ProductCategory(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the icon associated with the category.
     * @return icon as a String (emoji or icon path)
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns all categories with their icons as a list of DTOs.
     * @return List of ProductCategoryDTO
     */
    public static List<ProductCategoryDTO> getAllWithIcons() {
        List<ProductCategoryDTO> list = new ArrayList<>();
        for (ProductCategory category : values()) {
            list.add(new ProductCategoryDTO(category.name(), category.getIcon()));
        }
        return list;
    }
}
