package com.study.mvc.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.study.mvc.interceptor.MeasurementInterceptor;
import com.study.mvc.interceptor.SummaryReportInterceptor;

/**
 * 1. 다국어 지원을 위한 MessageSource 를 설정한다.
 * 2. Locale 변경을 위한 인터셉터를 정의한다. (LocaleChangeInterceptor)
 * 3. 사용할 localeResolver 를 정의한다. (이 예제에서는 CookieLocaleResolver 를 사용하기 로 함)
 * 4. HandlerMapping 의 interceptors 프로퍼티에 인터셉터를 설정한다.
 * 위 설정에 의해 요청 파라메터 "language" 에 설정된 값으로 로케일이 설정되며, 설정된 locale 정보는 쿠키에 저장된다.
 * 이후에 language 파라메터가 없는 요청에 대해서는 요청에 포함된 locale 관련 쿠키정보를 사용하여 로케일이 설정된다. 
 *
 */

@Configuration
public class LocaleAndInterceptorConfiguration implements WebMvcConfigurer {
    
//    /**
//     * 스프링의 기본 로케일 리좁러 accept-language 요청 헤더값에 따라 로케일 해석
//     * 유저 웹 브라우저는 자신을 실행한 운영체제의 로케일 설정으로 이 헤더를 설정
//     * 유저 운영체제의 로케일 설정을 바꿀 수는 없으므로 로케일 리졸버로 유저 로케일을 변경하는 것은 불가능
//     */
//    @Bean
//    AcceptHeaderLocaleResolver localeResolver() {
//        return new AcceptHeaderLocaleResolver();
//    }
    
//    /**
//     * 유저 세션에 사전 정의된 속성에 따라 로케일을 해석한다.
//     * 세션 속성이 없으면 accept-language 헤더로 기본 로케일을 결정한다.
//     * 로케일이 저장된 세션 속성을 변경함으로써 유저로케일을 변경한다.
//     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("en"));
//        return localeResolver;
//    }
    
    /**
     * 유저 브라우저 쿠키값에  따라 로케일을 해석한다.
     * 유저 브라우저 쿠키가 없으면 accept-language 헤더로 기본 로케일을 결정한다.
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver =  new CookieLocaleResolver();
        localeResolver.setCookieName("language");
        localeResolver.setCookieMaxAge(3600); //-1일 경우 브라우저 종료와 동시에 쿠키를 삭제
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }
    
    
    /**
     * URL의 language 매개변수를 이용해 유저 로케일을 바꿀 수 있게 설정하였다.
     * http://<http address>/...?language=en_US
     * http://<http address>/...?language=de
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(measurementInterceptor());
        
        /**
         * summaryReportInterceptor는 /reservationSummary URL에만 적용한다.
         */
        registry.addInterceptor(summaryReportInterceptor())
                .addPathPatterns("/reservationSummary*");    
        
        registry.addInterceptor(localeChangeInterceptor());
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
