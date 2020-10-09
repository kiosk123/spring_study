package com.study.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //AOP 사용
@ComponentScan("com.study.aop")
public class CalculatorConfiguration {

}
