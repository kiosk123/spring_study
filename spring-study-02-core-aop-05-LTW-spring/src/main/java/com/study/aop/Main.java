package com.study.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

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

        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
        complexCalculator.sub(new Complex(5, 8), new Complex(2, 3));
    }
}
