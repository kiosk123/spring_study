package com.apress.springrecipes.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanProfileMain02 {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context 
        = new AnnotationConfigApplicationContext();
        
        context.getEnvironment().setActiveProfiles("winter");
        context.scan("com.apress.springrecipes.shop");
        context.refresh();
    }
}
