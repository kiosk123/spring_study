package com.study.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.aop.ArithmeticCalculator;
import com.study.aop.config.CalculatorConfiguration;

public class AOPMain {
    public static void main(String[] args) {
        ApplicationContext context
            = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
        
        ArithmeticCalculator arithmeticCalculator
            = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);

    }

}
