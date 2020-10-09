package com.study.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
/**
 * @EnableAspectJAutoProxy AOP를 사용하겠다는 애너테이션이며
 * proxyTargetClass = true는 cglib(클래스) 프록시를 사용하겠다는 의미다.
 * 애너테이션으로 포인트 컷을 생성할때 반드시 이 옵션을 true로 해야한다.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true) 
@ComponentScan("com.study.aop")
public class CalculatorConfiguration {

}
