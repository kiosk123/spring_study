package com.apress.springrecipes.shop.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor를 이용하면 초기화 콜백 메서드(@PostConstruct) 전 후에 원하는 로직을 빈에 적용가능
 * 빈 후처리기의 주요 특징은 IoC 컨테이너 내부의 모두 빈 인스턴스를 대상으로 함(특정 빈 인스턴스 하나만이 아님)
 * 주로 빈 프로퍼티가 올바른지 체크하거나 어떤 기준에 따라 빈 프로퍼티를 변경 또는 전체 빈 인스턴스를  상대로 어떤 작업을
 * 수행하는 용도로 사용한다.
 * 
 * 예를 들어)
 * @Required는 스프링에 내장된 후처리기 RequiredAnnotationBeanPostProcessor가 지원하는 애너테이션이다.
 * RequiredAnnotationBeanPostProcessor 후처리기는 @Required를 붙인 모든 빈 프로퍼티가 설정되었는지 확인한다.
 */
@Component //이 처리기는 모든 빈 인스턴스를 처리한다.
public class AuditCheckBeanPostProcessor implements BeanPostProcessor {
    /**
     * 각 메서드는 아무 것도 안해도 원본 빈 인스턴스를 반환해야 한다.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AuditCheckBeanPostProcessor.postProcessBeforeInitialization" 
                + "proccessing bean type : " + bean.getClass());
        return bean; 
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
