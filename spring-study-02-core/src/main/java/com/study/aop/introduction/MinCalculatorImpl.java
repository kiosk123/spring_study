package com.study.aop.introduction;

public class MinCalculatorImpl implements MinCalculator {

    @Override
    public double min(double a, double b) {
        double result = (a <= b) ? a : b;
        System.out.printf("min(%s, %s) = %s\n",a,b,result);
        return result;
    }
}
