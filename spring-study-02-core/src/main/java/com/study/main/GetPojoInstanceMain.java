package com.study.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.shop.Product;
import com.study.shop.config.ShopConfiguration;

public class GetPojoInstanceMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);
        
        Product aaa = context.getBean("aaa", Product.class);
        System.out.println(aaa);
        
        Product cdrw = context.getBean("cdrw", Product.class);
        System.out.println(cdrw);
    }
}
