package com.study.shop.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.study.shop.Product;

/**
 * BeanPostProcessor로 특정 타입의 빈만 후처리하는 방법도 있다.
 */
@Component
public class ProductCheckBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product) {
            Product product = (Product)bean;
            String productName = product.getName();
            System.out.println("ProductCheckBeanPostProcessor.postProcessBeforeInitialization, "
                    + "processing Product: " + productName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product) {
            Product product = (Product)bean;
            String productName = product.getName();
            System.out.println("ProductCheckBeanPostProcessor.postProcessAfterInitialization, "
                    + "processing Product: " + productName);
        }
        return bean;
    }

}
