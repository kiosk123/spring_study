package com.study.aop;

import org.springframework.stereotype.Component;

@Component("unitCalculator")
public class UnitCalculatorImpl implements UnitCalculator {

    @Override
    public double kilogramToPound(double kilogram) {
        double pound = kilogram * 2.2;
        System.out.printf("%s kilgram = %s pound\n", kilogram, pound);
        return pound;
    }

    @Override
    public double kilmeterToMile(double kilometer) {
        double mile = kilometer * 0.62;
        System.out.printf("%s mile = %s kilometer\n", mile, kilometer);
        return mile;
    }

}
