package com.study.shop.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.study.shop.Product;

/** 
 * AbstractFactoryBean을 이용한 팩토리 구현
 */
public class DiscountFactoryBean extends AbstractFactoryBean<Product> {
    private Product product;
    private double discount;
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public Class<?> getObjectType() {
        return product.getClass();
    }

    /**
     * 상품가격에 할인 가격을 적용하고 상품빈을  반환
     */
    @Override
    protected Product createInstance() throws Exception {
        product.setPrice(product.getPrice() * (1-discount));
        return product;
    }

}
