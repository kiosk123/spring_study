package com.study.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CalculatorConfiguration {
    
    /**
     * 스프링에서 AspectJ를 제대로 구성하려면 프레임워크에서 일단 인스턴스를 가져와야한다.
     */
    @Bean
    public ComplexCachingAspect complexCachingAspect() {
        Map<String, Complex> cache = new HashMap<>();
        cache.put("2,3", new Complex(2, 3));
        cache.put("3,5", new Complex(3, 5));
        
        /**
         * Aspects.aspectOf()를 이용하여 현재 어스펙트 인스턴스에 엑세스한다.
         */
        ComplexCachingAspect complexCachingAspect
            = Aspects.aspectOf(ComplexCachingAspect.class);
        complexCachingAspect.setCache(cache);
        return complexCachingAspect;
    }
}

