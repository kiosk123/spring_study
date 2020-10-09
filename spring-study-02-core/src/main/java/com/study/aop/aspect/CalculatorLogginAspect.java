package com.study.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 포인트 컷은 어드바이스에 적용할 타입 및 객체를 찾는 표현식
 * 상세내용 참조
 * https://blog.naver.com/heo9910/220802922716
 */
@Aspect
@Component
public class CalculatorLogginAspect {
    private final static Logger log = LoggerFactory.getLogger(CalculatorLogginAspect.class);
    
    /**
     * 접근 제한자 public protected private 모두 매치하면서
     * 모든 반환형을 매치하고 ArithmeticCalculator의 n개(0개포함)인수를 갖고 있는 add() 메소드 호출전
     * 실행
     */
    @Before("execution(* com.study.aop.ArithmeticCalculator.add(..))")
    public void logBefore(JoinPoint jp) {
        log.info("The method add() begins");
        Object[] params = jp.getArgs();
        if (params != null && params.length > 0) {
            
        }
    }
}
