package com.apress.springrecipes.shop.factory;

import java.util.Map;

import com.apress.springrecipes.shop.Product;

/**
 * 인스턴스 팩토리 메서드
 */
public class ProductCreatorFactory {
    private Map<String, Product> products;

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
    
    public Product createProduct(String productId) {
        Product product = products.get(productId);
        if (product != null) {
            return product;
        }
        throw new IllegalArgumentException("Unknown product");
    }
}
