package com.study.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.study.sequence.SequenceGenerator;
import com.study.sequence.config.SequenceGeneratorConfiguration;

public class GetAnnotatedBeanMain {

    public static void main(String[] args) {
        /**
         * @Configuration 붙은 구성 클래스 통해  빈인스턴스를 가져옴
         */
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);
        
        // 인스턴스 가져오기 - 1번 방식
        SequenceGenerator generator = (SequenceGenerator)context.getBean("sequenceGenerator");
        System.out.println(generator.getSequence());
        
        // 인스턴스 가져오기 - 2번 방식
        generator = context.getBean(SequenceGenerator.class);
        System.out.println(generator.getSequence());
        
        // 인스턴스 가져오기 - 3번 방식 (특정 타입의 빈이 유일하지 않아서 이름과 동시 사용)
        generator = context.getBean("sequenceGenerator", SequenceGenerator.class);
        System.out.println(generator.getSequence());
    }

}
