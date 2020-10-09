package com.study.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 *  Orderd 인터페이스의 getOrder 함수를 구현하면 Aspect간 우선 순위를 결정할 수 있다.
 *  반환 값이 작을 수록 우선 순위가 높다
 *  
 *  그리고 @Order(0) 형식으로 우선순위 지정도 가능하다
 *   
 *  예)
 *  @Order(0)
 *  public class CalculatorValidationAspect
 */
@Aspect
@Component
public class CalculatorValidationAspect implements Ordered {
    
    private final static Logger log = LoggerFactory.getLogger(CalculatorValidationAspect.class);
    
    @Before("execution(* *.*(double, double))")
    public void validateBefore(JoinPoint jp) {
        log.info("check method {}() params ", jp.getSignature().getName());
        for (Object arg : jp.getArgs()) {
            validate((Double) arg);
        }
    }
    
    private void validate(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Positive numbers only");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
