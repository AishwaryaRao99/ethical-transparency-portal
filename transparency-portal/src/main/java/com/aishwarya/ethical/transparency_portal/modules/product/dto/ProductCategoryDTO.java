package com.aishwarya.ethical.transparency_portal.modules.product.dto;

/**
 * DTO for transferring product category and its icon.
 */
public class ProductCategoryDTO {
    private String category;
    private String icon;

    public ProductCategoryDTO(String category, String icon) {
        this.category = category;
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
