package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;

@ComponentScan("com.apress.springrecipes.shop")
@Configuration
public class ShopConfiguration {
    
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
}
