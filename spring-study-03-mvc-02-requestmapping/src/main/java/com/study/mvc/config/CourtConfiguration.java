package com.study.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.study.mvc")
public class CourtConfiguration {
    
    /**
     * JSP 템플릿 뷰 리졸버
     */
    @Bean
    InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver
            = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
