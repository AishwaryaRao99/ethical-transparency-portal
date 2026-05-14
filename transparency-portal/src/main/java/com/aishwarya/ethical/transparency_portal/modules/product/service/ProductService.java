package com.aishwarya.ethical.transparency_portal.modules.product.service;

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
     * Adds three dummy products to the database.
     */
    public void addDummyProducts() {
        ProductModel product1 = new ProductModel(
                "Eco-Friendly T-Shirt",
                "GreenWear",
                "https://example.com/images/tshirt.jpg",
                "A sustainable t-shirt made from organic cotton."
        );
        product1.setProductUrl("https://example.com/products/tshirt");
        product1.setPrice(29.99);
        product1.setStockQuantity(100);
        product1.setManufacturer("GreenWear Inc.");
        product1.setRawMaterials("Organic Cotton");
        product1.setProductionProcess("Low-impact dyeing");
        product1.setProductIngredients(Arrays.asList("Organic Cotton", "Natural Dye"));

        ProductModel product2 = new ProductModel(
                "Recycled Water Bottle",
                "EcoBottle",
                "https://example.com/images/bottle.jpg",
                "Reusable water bottle made from recycled plastic."
        );
        product2.setProductUrl("https://example.com/products/bottle");
        product2.setPrice(15.50);
        product2.setStockQuantity(200);
        product2.setManufacturer("EcoBottle Ltd.");
        product2.setRawMaterials("Recycled PET");
        product2.setProductionProcess("Recycling and molding");
        product2.setProductIngredients(Arrays.asList("Recycled PET"));

        ProductModel product3 = new ProductModel(
                "Vegan Leather Wallet",
                "PureVegan",
                "https://example.com/images/wallet.jpg",
                "Wallet made from cruelty-free vegan leather."
        );
        product3.setProductUrl("https://example.com/products/wallet");
        product3.setPrice(45.00);
        product3.setStockQuantity(50);
        product3.setManufacturer("PureVegan Co.");
        product3.setRawMaterials("Vegan Leather");
        product3.setProductionProcess("Synthetic leather crafting");
        product3.setProductIngredients(Arrays.asList("Polyurethane", "Cotton Lining"));

        List<ProductModel> products = Arrays.asList(product1, product2, product3);
        productRepository.saveAll(products);
    }
}