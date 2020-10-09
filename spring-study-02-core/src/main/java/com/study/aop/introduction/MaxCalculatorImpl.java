package com.study.aop.introduction;

public class MaxCalculatorImpl implements MaxCalculator {

    @Override
    public double max(double a, double b) {
        double result = (a >= b) ? a : b;
        System.out.printf("max(%s, %s) = %s\n",a,b,result);
        return result;
    }

}
