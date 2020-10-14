package com.study.mvc.config;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * 1. web.xml 역할을 하는 파일 작성법
 * ServletContainerInitializer 인터페이스를 구현한다.
 * META-INF/services 디렉터리에 javax.servlet.ServletContainerInitializer 파일을 생성한다.
 * javax.servlet.ServletContainerInitializer 파일안에 ServletContainerInitializer 인터페이스를 구현 클래스의 패키지명 포함한 클래스명을 입력 후 저장한다.
 */
public class CourtServletContainerInitializer implements ServletContainerInitializer {

    public static final String MSG = "Starting Court Web Application";

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        System.out.println(MSG);

        ctx.log(MSG);

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        
        //참조할 구성 클래스 설정
        applicationContext.register(CourtConfiguration.class);

        /**
         * 대규모 애플리케이션에서는 DispatcherServlet 여러개 두고 DispatcherServlet마다 특정 URL을 매핑해서
         * DispatcherServlet이 특정 URL을 전담하게 설계하지만 여기서는 하나만 등록하고 /(루트디렉터리)가 포함된 모든
         * URL을 매핑하도록 설정하였다.
         */
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic courtRegistration = ctx.addServlet("court", dispatcherServlet);
        courtRegistration.setLoadOnStartup(1);
        courtRegistration.addMapping("/");
    }
}
