package com.apress.springrecipes.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.aware.config.AwareConfiguration;

public class AwareImplementationMain {

    public static void main(String[] args) {
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(AwareConfiguration.class);
    }
}
