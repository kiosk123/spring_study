package com.study.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * 
 * @Configurable + @Component의 컴플렉스 형 객체
 * 즉 @Configurable를 인스턴스화 한 것이다.
 *
 */
@Configurable
@Component
@Scope("prototype")
public class Complex {

    private int real;
    private int imaginary;
    private ComplexFormatter formatter;

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    @Autowired
    public void setFormatter(ComplexFormatter formatter) {
        this.formatter = formatter;
    }

    public String toString() {
        return formatter.format(this);
    }
}