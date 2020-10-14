package com.study.mvc.config;

//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 2. web.xml 역할을 하는 파일 작성법
 * 1. 번 방법과 달리 META-INF에 파일 생성을 하지 않아도 됨
 *
 */
//public class CourtWebApplicationInitializer 
//    extends AbstractAnnotationConfigDispatcherServletInitializer{
// 
//    /*백 엔드  Configuration 설정*/
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return null;
//    }
// 
//    /*MVC Configuration 설정*/
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{CourtConfiguration.class};
//    }
// 
//    /*DispatcherServlet root path 설정*/
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}