package com.study.mvc.config;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * web.xml 역할
 * DispatcherServlet 인스턴스 정의 
 * DispatcherServlet 인스턴스는 여러개 둘 수 있고 대규모 애플리케이션 개발시 
 * 특정 URL을 전담하도록 설계할 수 있어 코드관리가 쉬워짐 
 * 
 * ServletContainerInitializer의 구현 파일은 패키지 경로까지 포함된 풀네임을
 * META-INF/services 디렉터리의  파일명이 javax.servlet.ServletContainerInitializer인 
 * 파일에 추가한다.
 * 
 * 서블릿 컨테이너는 이 파일을 로드해 애플리케이션을 시동할 때 사용한다.
 */
public class CourtServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext 
            = new AnnotationConfigWebApplicationContext();
        applicationContext.register(CourtConfiguration.class);
        
        // DispatcherServlet 웹 요청을 받아서 적절한 핸들러에 전달한다. 여기서 서블릿 이름은 court라고 지었다.
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic courtRegistration
            = ctx.addServlet("court", dispatcherServlet);
        courtRegistration.setLoadOnStartup(1);
        
        /**
         * '/'가 포함된 모든 URL을 매핑한다('/'는 루트 디렉터리이며).
         * URL패턴은 더 잘게 나누어 지정할 수 있다. 대규모 애플리케이션에서는
         * 여러 DispatcherServlet을 만들어 URL 패턴별로 위임하는 게 더 바람직하지만
         * 여기서는 편의상 이렇게 구성한다.
         */
        courtRegistration.addMapping("/"); 
    }
}
