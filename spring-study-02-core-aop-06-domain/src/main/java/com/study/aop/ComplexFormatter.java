package com.study.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 복소수를 문자열로 바꿀때 사용한다.
 * @author USER
 *
 */
@Component
public class ComplexFormatter {

    @Value("(a + bi)")
    private String pattern;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String format(Complex complex) {
        return pattern.replaceAll("a", Integer.toString(complex.getReal()))
                .replaceAll("b", Integer.toString(complex.getImaginary()));
    }
}
