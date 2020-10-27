package com.study.mvc.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.study.mvc.config.CourtConfiguration;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer를 상속하면 
 * 이클래스에 등록된 DispatcherServlet과 필터의 isAsyncSupported 프로퍼티가 이미켜져있음
 * 비동기 모드를 켜고 끄는 분기 로직을 직접 구현하려면 setAsyncSupported()를 오버라이드해야함
 */
public class CourtWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{CourtConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
