package com.apress.springrecipes.main;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.config.ShopConfiguration;

public class MessageSourceLocaleMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);
        
        String alert = context.getMessage("alert.checkout", null, Locale.US);
        String alert_inventry = context.getMessage("alert.inventory.checkout",
                new Object[] {"[DVD-RW 3.0]", new Date()}, Locale.US);
        
        System.out.println(alert);
        System.out.println(alert_inventry);

    }

}
