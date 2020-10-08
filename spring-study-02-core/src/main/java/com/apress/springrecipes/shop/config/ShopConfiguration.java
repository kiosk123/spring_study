package com.apress.springrecipes.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;

@PropertySource("classpath:discounts.properties")
@ComponentScan("com.apress.springrecipes.shop")
@Configuration
public class ShopConfiguration {
    
    /**
     * properties 파일에서 endofyear.discount의 값을 읽어서 할당
     * endofyear.discount가 없다면 디폴드 값이 0으로 설정됨
     */
    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscountField;
    
    @Bean
    public Product aaa() {
        Battery p1 = new Battery("AAA", 2.5);
        p1.setRechargeable(true);
        return p1;
    }
    
    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5);
        p2.setCapacity(700);
        return p2;
    }
    
    @Bean
    public Product dvdrw() {
        Disc p3 = new Disc("DVD-RW", 3.0);
        p3.setCapacity(700);
        return p3;
    }
    
    
    /**
     * 
     * @PropertySource 사용시 세트로 함께 사용한다.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer 
        propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
