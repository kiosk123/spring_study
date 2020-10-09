package com.study.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.shop.Product;
import com.study.shop.config.ShopConfiguration;

public class BeanFactoryMain {

    public static void main(String[] args) {
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(ShopConfiguration.class);
        
        Product cdrw1 = context.getBean("cdrw", Product.class);
        Product cdrw2 = context.getBean("discountFactoryBeanCDRW", Product.class);

        System.out.println(cdrw1 == cdrw2);
    }

}
