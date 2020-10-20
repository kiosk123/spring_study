package com.study.mvc.config;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.study.mvc.exception.ReservationNotAvailableException;

@Configuration
public class ExceptionHandlerConfiguration implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(handlerExceptionResolver());
    }
    
    /**
     * unchecked 예외는 HandlerExceptionResolver 인터페이스를 구현한 커스텀 예외 리졸버로 해석할 수 있다.
     * 보통 예외 카테고리별로 각각의 에러 페이지를 매핑한다.
     * 스프링 MVC에 탑재된 SimpleMappingExceptionResolver를 이용하면 웹 애플리케이션 컨텍스트에서
     * 발생한 예외를 매핑할 수 있다.
     */
    public SimpleMappingExceptionResolver handlerExceptionResolver() {
        /**
         * exceptionMapping 프로퍼티에 java.lang.Exception 방향으로 점점 일반화한 예외 클래스를 지정하면
         * 예외 클래스를 몇 개라도 추가할 수 있다. 이와 같이 발생한 예외 클래스형에 따른 뷰를 유저에게 보여주게 된다.
         */
        Properties exceptionMapping = new Properties();
        
        /**
         * ReservationNotAvailableException 클래스를 reservationNotAVailable 논리 뷰에 매핑
         */
        exceptionMapping.setProperty(ReservationNotAvailableException.class.getName(), "reservationNotAVailable");
        SimpleMappingExceptionResolver exceptionResolver 
            = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(exceptionMapping);
        
        /**
         * 매핑 되지 않은 뷰는 논리뷰 이름이 error에 매핑된다.
         */
        exceptionResolver.setDefaultErrorView("error");
        return exceptionResolver;
    }
}
