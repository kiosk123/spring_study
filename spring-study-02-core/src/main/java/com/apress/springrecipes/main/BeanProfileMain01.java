package com.apress.springrecipes.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.profile.ShopConfigurationSumWin;

public class BeanProfileMain01 {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "winter");
        
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(ShopConfigurationSumWin.class);
    }
}
