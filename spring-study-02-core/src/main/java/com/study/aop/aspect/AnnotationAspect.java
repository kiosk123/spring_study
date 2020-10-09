package com.study.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspect {
    
    private final static Logger log = LoggerFactory.getLogger(AnnotationAspect.class);
    
    /**
     * @LoggingRequired 애너테이션이 붙은 타겟을 조인포인트로 매칭한다.
     */
    @Pointcut("@annotation(com.study.aop.anno.LoggingRequired)")
    public void loggingOperation() {}
    
    @Before("loggingOperation()")
    public void logBefore(JoinPoint jp) {
        log.info("call method {}()", jp.getSignature().getName());
    }
}
