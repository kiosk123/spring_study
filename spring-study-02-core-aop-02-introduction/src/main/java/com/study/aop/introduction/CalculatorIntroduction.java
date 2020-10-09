package com.study.aop.introduction;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import com.study.aop.Counter;
import com.study.aop.CounterImpl;
import com.study.aop.MaxCalculator;
import com.study.aop.MaxCalculatorImpl;
import com.study.aop.MinCalculator;
import com.study.aop.MinCalculatorImpl;

@Aspect
@Component
public class CalculatorIntroduction {
    /**
     * @DeclareParents을 이용해서 인터페이스 구현체를 인트로덕션(끌어들임)한다. 
     * value는 상속받을 대상이 되는 인터페이스 타입의 구현클래스 타입(동적으로 기능이 추가되는 대상)이며
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
    
    
    /**
     * 각 CalculatorImpl 구현체에 기능이 동적으로 추가된다.
     */
    @DeclareParents(
            value = "com.study.aop.*CalculatorImpl",
            defaultImpl = CounterImpl.class)
    Counter counter;
    
    /**
     * 동적으로 추가한 counter를 이용하여 조인포인트 실행 후 counter.increase() 
     * 메소드를 호출하여 실행한다.
     * 
     * 그리고 여기서는 Counter 인터페이스를 구현한 객체는 프록시가 유일하므로 반드시 target이 아닌 this를 사용한다.
     */
    @After("execution(* com.study.aop.*Calculator.*(..)) && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
    
}
