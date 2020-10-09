package com.study.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.aop.ArithmeticCalculator;
import com.study.aop.Counter;
import com.study.aop.MaxCalculator;
import com.study.aop.MinCalculator;
import com.study.aop.UnitCalculator;
import com.study.aop.config.CalculatorConfiguration;

public class AOPMain {
    public static void main(String[] args) {
        ApplicationContext context
            = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
        
        ArithmeticCalculator arithmeticCalculator
            = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);
        
        UnitCalculator unitCalculator 
            = context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(10);
        
        arithmeticCalculator.sub(12, 0);
//        arithmeticCalculator.div(12, 0); //throw IllegalArgumentException
        
        // 인트로 덕션 테스트
        MaxCalculator maxCalculator = (MaxCalculator)arithmeticCalculator;
        System.out.println("max value : " + maxCalculator.max(1, 2));
        
        MinCalculator minCalculator = (MinCalculator)arithmeticCalculator;
        System.out.println("min value : " + minCalculator.min(1, 2));
        
        
        Counter arithmeticCounter = (Counter)arithmeticCalculator;
        System.out.println("ArithmeticCalculator method call count : " + arithmeticCounter.getCount());
        
        Counter unitCounter = (Counter)unitCalculator;
        System.out.println("UnitCalculator method call count : " + unitCounter.getCount());
        
    }
}
