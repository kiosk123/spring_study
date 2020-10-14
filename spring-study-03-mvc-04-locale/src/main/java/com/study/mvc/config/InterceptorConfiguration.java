package com.study.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.study.mvc.interceptor.MeasurementInterceptor;
import com.study.mvc.interceptor.SummaryReportInterceptor;

/**
 * 인터셉터는 기본적으로 모든 @Controller에 적요되지만 원하는 컨트롤러만 선택적으로 적용할 수 있다.
 * 
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(measurementInterceptor());
        
        /**
         * summaryReportInterceptor는 /reservationSummary URL에만 적용한다.
         */
        registry.addInterceptor(summaryReportInterceptor())
                .addPathPatterns("/reservationSummary*");      
    }

    @Bean
    public MeasurementInterceptor measurementInterceptor() {
        return new MeasurementInterceptor();
    }
    
    @Bean
    public SummaryReportInterceptor summaryReportInterceptor() {
        return new SummaryReportInterceptor();
    }
}
