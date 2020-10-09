package com.study.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.aop.ArithmeticCalculator;
import com.study.aop.LoggingRequiredTarget;
import com.study.aop.ParamsPointCutTarget;
import com.study.aop.UnitCalculator;
import com.study.aop.config.CalculatorConfiguration;
import com.study.aop.introduction.Counter;
import com.study.aop.introduction.MaxCalculator;
import com.study.aop.introduction.MinCalculator;

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
        
        LoggingRequiredTarget lrt = context.getBean(LoggingRequiredTarget.class);
        lrt.call();
        
        ParamsPointCutTarget ppct = context.getBean(ParamsPointCutTarget.class);
        ppct.printParams(1, 1.2);
        
        //인트로덕션 테스트
        MaxCalculator maxCalculator = (MaxCalculator)arithmeticCalculator;
        System.out.println(maxCalculator.max(10, 1));
        
        MinCalculator minCalculator = (MinCalculator)arithmeticCalculator;
        System.out.println(minCalculator.min(10, 1));
        
        Counter arithmetiCounter = (Counter)unitCalculator;
        System.out.println(arithmetiCounter.getCount());
    }

}
