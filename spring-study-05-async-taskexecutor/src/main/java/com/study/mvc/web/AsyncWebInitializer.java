package com.study.mvc.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * web.xml 역할
 * WebApplicationInitializer를 이용한 비동기 처리 설정
 *
 */
//public class AsyncWebInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        DispatcherServlet servlet = new DispatcherServlet();
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", servlet);
//        registration.setAsyncSupported(true);
//        
//    }
//}
