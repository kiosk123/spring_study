package com.study.mvc.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put("pdf", MediaType.valueOf("application/pdf"));
        mediaTypes.put("xls", MediaType.valueOf("application/vnd.ms-excel"));
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        configurer.mediaTypes(mediaTypes);
    }
    
    /**
     *  콘텐트 협상이 잘 동작하려면 ContentNegotiatingViewResolver의 우선 순위를 가장 높게 설정해야한다.
     *  이 리졸버는 직접 뷰를 해석하지 않고 자신이 자동 감지한 다른 리졸버에게 그작업을 넘기기 때문이다.
     *  이 리졸버가 미디어 타입을 결정하는 과정은 다음과 같다.
     *  1. 요청 경로에 확장자(.html, .xml, .pdf등..)는 있지만 기본 미디어 타입과 매치되는 확장자가 없으면,
     *     자바 액티베이션 프레임워크의 FileTypeMap을 이용하여 확장자의 미디어 타입을 경정한다.
     *  2. 요청 경로에 확장작 없으면 HTTP Accept 헤더를 이용한다
     *  
     *  ex) /reservationSummary.xml은 
     *      1.에서 미디어 타입이 application/xml로 확정되고
     *      /reservationSummary 요청은 
     *      2.에서 Accept헤더값 까지 확인되어야 한다.
     *  
     *  3. 미디어 타입을 알아내고 요청 URL에 대한 논리뷰 (예를들어 reservation)를 얻었으면 
     *     이정보를 바탕으로 지정한 우선순위에 따라 차례대로 나머지 리졸버를 순회하면서 논리뷰에 가장 적합한 뷰를 결정한다.
     */
    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager contentNegotiationManager) {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setContentNegotiationManager(contentNegotiationManager);
        return viewResolver;
    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
