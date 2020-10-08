package com.apress.springrecipes.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.SequenceDao;

public class ScannedBeanMain {

    public static void main(String[] args) {
        ApplicationContext context 
            = new AnnotationConfigApplicationContext("com.apress.springrecipes.sequence");
        
        SequenceDao sequenceDao = context.getBean(SequenceDao.class);
        System.out.println(sequenceDao.getNextValue("IT"));
        System.out.println(sequenceDao.getNextValue("IT"));
    }

}
