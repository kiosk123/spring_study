package com.study.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.sequence.SequenceDao;

public class ScannedBeanMain {

    public static void main(String[] args) {
        ApplicationContext context 
            // 파라미터르로 빈 스캔 베이스 패키지를 설정
            = new AnnotationConfigApplicationContext("com.study.sequence");
        
        SequenceDao sequenceDao = context.getBean(SequenceDao.class);
        System.out.println(sequenceDao.getNextValue("IT"));
        System.out.println(sequenceDao.getNextValue("IT"));
    }

}
