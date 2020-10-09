package com.study.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanProfileMain02 {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context 
        = new AnnotationConfigApplicationContext();
        
        context.getEnvironment().setActiveProfiles("winter");
        context.scan("com.study.shop");
        context.refresh();
    }
}
