package com.study.aop;

import org.springframework.stereotype.Component;

import com.study.aop.anno.LoggingRequired;

@Component("arithmeticCalculator")
@LoggingRequired
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    
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
