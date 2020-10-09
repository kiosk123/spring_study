package com.apress.springrecipes.shop.factory;

import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;

/**
 * 정적 팩토리 메서드로 인스턴스 생성
 */
public interface ProductCreator {
    public static Product createProduct(String productId) {
        if ("aaa".equals(productId)) {
            return new Battery("AAA", 2.5);
        }
        else if ("cdrw".equals(productId)) {
            return new Disc("CD-RW", 1.5);
        }
        else if("dvdrw".equals(productId)) {
            return new Disc("DVD-RW", 3.0);
        }
        throw new IllegalArgumentException("Unkown product");
    }
}
