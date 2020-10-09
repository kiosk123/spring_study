package com.study.aop;

import org.springframework.stereotype.Component;

@Component
public class ParamsPointCutTarget {
    public void printParams(int a, double b) {
        System.out.printf("passed param values : %s, %s\n",a,b);
    }
}
