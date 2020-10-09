package com.study.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectCommon {
    
    /**
     * 포인트 컷 재활용 위한 포인트 컷 선언
     * 여러 애스펙트가 포인트 컷을 공유하는 경우라면 공통 클래스에 선언 후
     * 접근제한자는 public으로 선언
     */
    @Pointcut("within(com.study.aop.*)") 
    public void loggingOperation() {}
    
    /**
     * within을 활용하여  조인포인트를 매칭가능 하다.
     * within(com.study.aop) com.study.aop 패키지의 모든 조인포인트 매칭 
     * within(com.study.aop.*) com.study.aop 하위 패키지 포함 모든 조인포인트 매칭 
     * within(com.study.aop.ArithmeticCalculatorImpl) 
     *  com.study.aop.ArithmeticCalculatorImpl내 메소드 조인포인트 매칭
     *  같은 패키지인 경우 패키지명을 생략해도됨 ex) within(ArithmeticCalculatorImpl) 
     * 
     * within(ArithmeticCalculator+) 인터페이스를 구현한 모든 클래스의 메서드 실행 조인포인트를 매치
     * 
     */
    
    /**
     * @LoggingRequired 애너테이션이 붙은 대상(클래스나 메소드)을 조인포인트로 설정
     */
    @Pointcut("@annotation(com.study.aop.anno.LoggingRequired)")
    public void loggingAnnotation() {};
}
