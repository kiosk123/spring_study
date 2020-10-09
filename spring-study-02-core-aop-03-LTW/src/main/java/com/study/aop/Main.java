package com.study.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        /**
         * java -javaagent:lib/<aspectjweaver jar 파일명>.jar -jar <패키징된 jar파일명>.jar
         * ex) java -javaagent:lib/aspectjweaver-1.9.6.jar -jar <패키징된 jar파일>.jar
         */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ComplexCalculator complexCalculator =
                context.getBean("complexCalculator", ComplexCalculator.class);

        /**
         * 작동은 잘되지만 성능을 높이기 위해서는 복소수 객체를 캐시하는 편이 좋다.
         * 캐싱은 전형적인 공통 관심사이므로 애스펙트로 모듈화하기에 좋다.
         */
        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
        complexCalculator.sub(new Complex(5, 8), new Complex(2, 3));
    }
}
