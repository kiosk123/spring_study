package com.study.aop.introduction;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {
    /**
     * @DeclareParents 인트로덕션 대상 클래스 지정
     * value = 인트로덕션 대상 클래스
     * defaultImpl = 새 인터페이스에서 사용할 구현 클래스
     */
    @DeclareParents(
            value = "com.study.aop.ArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;
    
    @DeclareParents(
            value = "com.study.aop.ArithmeticCalculatorImpl",
            defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;
    
    @DeclareParents(
            value = "com.study.aop.introduction.*CalculatorImpl",
            defaultImpl = CounterImpl.class)
    public Counter counter;
    
    /**
     * 인터페이스를 구현한 객체는 프록시가 유일하므로 target이 아닌 this를 이용해 사용
     */
    @After("execution(* com.study..*Calculator.*(..))"
            + " && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
}
