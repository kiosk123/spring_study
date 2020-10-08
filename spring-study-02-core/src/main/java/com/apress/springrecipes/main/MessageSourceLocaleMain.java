package com.apress.springrecipes.main;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.Cashier;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ShoppingCart;
import com.apress.springrecipes.shop.config.ShopConfiguration;

public class MessageSourceLocaleMain {

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);
        
        String alert = context.getMessage("alert.checkout", null, Locale.US);
        String alert_inventry = context.getMessage("alert.inventory.checkout",
                new Object[] {"[DVD-RW 3.0]", new Date()}, Locale.US);
        
        System.out.println(alert);
        System.out.println(alert_inventry);
        
        ShoppingCart shoppingCart = context.getBean(ShoppingCart.class);
        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        
        shoppingCart.addItem(aaa);
        shoppingCart.addItem(cdrw);
        
        Cashier cashier = context.getBean(Cashier.class);
        cashier.checkout(shoppingCart);
    }
}
