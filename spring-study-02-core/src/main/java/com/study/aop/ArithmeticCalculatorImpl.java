package com.study.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.study.aop.aspect.CalculatorLogginAspect;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

    private final static Logger log = LoggerFactory.getLogger(CalculatorLogginAspect.class);
    
    @Override
    public double add(double a, double b) {
        double result = a + b;
        System.out.printf("%s + %s = %s\n", a, b, result);
        return result;
    }

    @Override
    public double sub(double a, double b) {
        double result = a - b;
        System.out.printf("%s - %s = %s\n", a, b, result);
        return result;
    }

    @Override
    public double mul(double a, double b) {
        double result = a * b;
        System.out.printf("%s * %s = %s\n", a, b, result);
        return result;
    }

    @Override
    public double div(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        double result = a / b;
        System.out.printf("%s / %s = %s\n", a, b, result);
        return result;
    }

}
