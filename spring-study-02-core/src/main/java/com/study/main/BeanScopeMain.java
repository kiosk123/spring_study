package com.study.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.shop.Cashier;
import com.study.shop.ShoppingCart;
import com.study.shop.config.ShopConfiguration;

public class BeanScopeMain {

    public static void main(String[] args) {
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(ShopConfiguration.class);
        
        ShoppingCart shoppingCart1 = context.getBean(ShoppingCart.class);
        ShoppingCart shoppingCart2 = context.getBean(ShoppingCart.class);
        
        //prototype scope로 인스턴스를 생성하여 가져오기 때문에 false
        System.out.println(shoppingCart1 == shoppingCart2);
    }
}
