package com.study.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@EnableLoadTimeWeaving //스프링 로드 타임 위버 사용
@ComponentScan
public class CalculatorConfiguration {
}
