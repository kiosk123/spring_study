//package com.study.mvc;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.http.server.reactive.HttpHandler;
//import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
//
//public class WebFluxCustomInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigApplicationContext context 
//            = new AnnotationConfigApplicationContext(WebFluxConfigurer.class);
//        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
//        /**
//         * 런타임에 따라 시동 방법이 달라짐 
//         * ServletHttpHandlerAdapter 서블릿 3.1, 톰캣, 제티
//         * TomcatHttpHandlerAdaper 톰캣
//         * JettyHttpHandlerAdaper 제티
//         * ReactorhttpHandlerAdaper 리액터 네티
//         * RxNettyHttpHandlerAdaper Rx네트
//         * UndertowHttpHandlerAdapter 언더토우
//         */
//        ServletHttpHandlerAdapter handlerAdapter 
//            = new ServletHttpHandlerAdapter(handler);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher-handler", handlerAdapter);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/");
//        
//    }
//}
