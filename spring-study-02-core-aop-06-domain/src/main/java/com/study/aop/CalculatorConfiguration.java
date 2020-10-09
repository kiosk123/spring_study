package com.study.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
/**
 * @Configurable 붙은 클래스를 인스턴스화 하면 애스펙트는 클래스와 
 *  @Configurable 붙은 클래스와 동일한 타입의 프로토타입 스코프 빈을 찾는다.
 *  그런다음, 빈 정의부 내용에 따라 새 인스턴스를 구성한다.
 *  빈정의부에 프로퍼티가 선언되어 있으면 새 인스턴스도 애스펙트가 설정한 것과 동인한 프로퍼티를
 *  갖는다.
 *
 */
@EnableSpringConfigured 
@ComponentScan
public class CalculatorConfiguration {
}
