package com.study.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 포인트 컷은 어드바이스에 적용할 타입 및 객체를 찾는 표현식
 * 상세내용 참조
 * https://blog.naver.com/heo9910/220802922716
 */
@Aspect
@Component
public class CalculatorLogginAspect implements Ordered {
    private final static Logger log = LoggerFactory.getLogger(CalculatorLogginAspect.class);
    
    
    @Override
    public int getOrder() {
        return 1;
    }
    
    /**
     * 포인트 컷 재활용 위한 포인트 컷 선언
     * 여러 애스펙트가 포인트 컷을 공유하는 경우라면 공통 클래스에 선언 후
     * 접근제한자는 public으로 선언
     */
    @Pointcut("execution(* *.*(..))") 
    private void loggingOperation() {}

    //조인포인트 정보 가져오기
    @Before("loggingOperation()") //포인트 컷 재활용
    public void logJoinPoint(JoinPoint jp) {
        log.info("Join point kind : {}", jp.getKind());
        log.info("Signature declaring type : {}", 
                jp.getSignature().getDeclaringType());
        log.info("Signature name : {}", jp.getSignature().getName());
        log.info("Arguments : {}", Arrays.toString(jp.getArgs()));
        log.info("Target class : {}", jp.getTarget().getClass().getName()); //타겟 정보
        log.info("This class : {}", jp.getThis().getClass().getName()); //프록시 정보
    }
    
    /**
     * 접근 제한자 public protected private 모두 매치하면서
     * 모든 반환형을 매치하고 ArithmeticCalculator의 
     * n개(0개포함)의 인수를 갖고 있는 add() 메소드 호출전 실행
     */
    @Before("execution(* com.study.aop.ArithmeticCalculator.add(..))")
    public void logBefore(JoinPoint jp) {
        log.info("The method add() begins");
        Object[] params = jp.getArgs();
        if (params != null && params.length > 0) {
           int num = 1;
           for (Object param : params) {
               log.info("passing the param {} : value {}", num, param);
               num++;
           }
        }
    }
    
    /**
     * @After에 선언된 포인트 컷의 조인포인트 끝난 후 실행
     * 조인포인트 실행 후 예외가 발생하든, 성공하든지 무조건 실행
     */
    @After("execution(* *.*(..))")
    public void logAfter(JoinPoint jp) {
        log.info("The method {} end",jp.getSignature().getName());
    }
    
    /**
     * @AfterReturning 조인포인트의 실행 성공여부와 상관없이 동작
     * 조인포인트에서 값을 반환하는 경우에만 실행하고 싶을때 사용
     */
    @AfterReturning(pointcut = "loggingOperation()", returning = "result") //포인트 컷 재활용
    public void logAfterReturning(JoinPoint jp, Object result) {
        log.info("The method {}() ends with {}", jp.getSignature().getName(), result);
    }
    
    /**
     * @AfterThrowing
     * 조인 포인트에서 에러가 발생했을 때 사용
     */
    @AfterThrowing(value = "execution(* *.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint jp, Throwable e) {
        log.error("An exception {} has been thrown in {}()", 
                e, 
                jp.getSignature().getName());
    }
    
    /**
     *  @Around 가장 강력한 기능의 어드바이스
     *  조인포인트를 완전 장악한다.(조인포인트를 감쌈)
     *  조인포인트를 언제 실행할지, 실행을 하지말지, 계속 실행할지 여부도 제어가능하다.
     */
    @Around("execution(* *.*(..))")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable {
        
        log.info("The method {}() begins with {}", jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        Object result = null;
        try {
            result = jp.proceed();
            log.info("The method {}() ends with ", jp.getSignature().getName());
        }
        catch (IllegalArgumentException e) {
            log.error("Illegal argument {} in {}()", Arrays.toString(jp.getArgs()),
                    jp.getSignature().getName());
            throw e;
        }

        return result;
    }
}
