package com.study.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CombinePointCutAspect {
    
    private final static Logger log = LoggerFactory.getLogger(CombinePointCutAspect.class);
    
    @Pointcut("within(com.study.aop.ArithmeticCalculator+)")
    public void arithmeticOperation() {}
    
    @Pointcut("within(com.study.aop.UnitCalculator+)")
    public void unitOperation() {}
    
    /**
     * 여러개의 포인트 컷을 연산자로 묶을 수 있다
     * ||(or), &&(and), !(not)
     */
    @Before("arithmeticOperation() || unitOperation()")
    public void loggingOperation() {
        log.info("combine pointcut operation");
    }
}
