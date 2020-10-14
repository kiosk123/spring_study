package com.study.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *  web.xml 역할
 */
public class CourtWebApplicationInitializer 
    extends AbstractAnnotationConfigDispatcherServletInitializer{
 
    /*백 엔드  Configuration 설정*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
 
    /*MVC Configuration 설정*/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{CourtConfiguration.class};
    }
 
    /*DispatcherServlet root path 설정*/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}