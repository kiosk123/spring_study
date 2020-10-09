package com.study.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParamsPointcutAspect {
    
    private final static Logger log = LoggerFactory.getLogger(ParamsPointcutAspect.class);
    
    @Pointcut("execution(* com.study.aop.ParamsPointCutTarget.*(..)) && target(target) && args(a,b)")
    public void parameterPointcut(Object target, int a, double b) {};
    
    
    @Before("parameterPointcut(target, a, b)")
    public void logParams(Object target, int a, double b) {
        log.info("target Class : " + target.getClass().getName());
        log.info("Arguments : {}, {}",a,b);
    }
}
