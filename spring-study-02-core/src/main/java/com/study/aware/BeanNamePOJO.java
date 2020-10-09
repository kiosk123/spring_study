package com.study.aware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * BeanNameAware을 이용하면 POJO는 자신의 빈이름을 인지
 */
@Component
public class BeanNamePOJO implements BeanNameAware {

    private String beanName;
    
    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        
    }
    
    @PostConstruct
    public void init() {
        System.out.println("Initialized bean name is " + beanName);
    }
    
    @PreDestroy
    public void destory() {
        System.out.println("Destryed bean name is " + beanName);
    }
    
}
