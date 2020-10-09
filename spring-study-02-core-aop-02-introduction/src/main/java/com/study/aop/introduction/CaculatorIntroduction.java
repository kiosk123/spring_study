package com.study.aop.introduction;

import org.aspectj.lang.annotation.DeclareParents;

import com.study.aop.MaxCalculator;
import com.study.aop.MaxCalculatorImpl;
import com.study.aop.MinCalculator;

public class CaculatorIntroduction {
    /**
     * @DeclareParents을 이용해서 인터페이스 구현체를 인트로덕션(끌어들임)한다. 
     * value는 상속받을 대상이 되는 인터페이스 타입의 구현클래스 타입이며
     * defaultImpl은 인트로덕션 대상이 되는 인터페이스의 구현체 클래스이다.
     */
    @DeclareParents(
            value = "com.study.aop.ArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;
    
    @DeclareParents(
            value = "com.study.aop.ArithmeticCalculatorImpl",
            defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;
}
