package com.apress.springrecipes.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.SequenceDao;

public class ScannedBeanMain {

    public static void main(String[] args) {
        ApplicationContext context 
            // 파라미터르로 빈 스캔 베이스 패키지를 설정
            = new AnnotationConfigApplicationContext("com.apress.springrecipes.sequence");
        
        SequenceDao sequenceDao = context.getBean(SequenceDao.class);
        System.out.println(sequenceDao.getNextValue("IT"));
        System.out.println(sequenceDao.getNextValue("IT"));
    }

}
