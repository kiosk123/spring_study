package com.study.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

public class Main {

    public static void main(String[] args) {

        /**
         * @EnableLoadTimeWeaving을 구성 클래스에 사용하여 스프링 로드타임 위버로 위빙이 가능하다
         * 
         * java -javaagent:lib/<spring-instrument jar 파일명>.jar -jar <패키징된 jar파일명>.jar
         * ex) java -javaagent:lib/spring-instrument-5.0.0.jar -jar <패키징된 jar파일>.jar
         */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ComplexCalculator complexCalculator =
                context.getBean("complexCalculator", ComplexCalculator.class);

        /**
         * 복소수 객체를 생성한 곳이 메인일 경우 IoC 컨테이너 빈이 아니기 때문에
         * 스피링 로드타임 위빙이 적용되지 않아서 어드바이스가 실행되지 않는다.
         */
        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
        complexCalculator.sub(new Complex(5, 8), new Complex(2, 3));
    }
}
