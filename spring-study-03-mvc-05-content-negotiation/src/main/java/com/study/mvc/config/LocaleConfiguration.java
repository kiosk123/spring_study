package com.study.mvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

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
public class LocaleConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * URL의 language 매개변수를 이용해 유저 로케일을 바꿀 수 있게 설정하였다. http://<http
     * address>/...?language=en_US http://<http address>/...?language=de
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    /**
     * 유저 브라우저 쿠키값에 따라 로케일을 해석한다. 유저 브라우저 쿠키가 없으면 accept-language 헤더로 기본 로케일을 결정한다.
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setCookieMaxAge(3600);
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        return cookieLocaleResolver;
    }

    /**
     * 다국어 지원 MessageSource 로드
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

//    /**
//     * 스프링의 기본 로케일 리졸버 accept-language 요청 헤더값에 따라 로케일 해석 유저 웹 브라우저는 자신을 실행한 운영체제의
//     * 로케일 설정으로 이 헤더를 설정 유저 운영체제의 로케일 설정을 바꿀 수는 없으므로 로케일 리졸버로 유저 로케일을 변경하는 것은 불가능
//     */
//    @Bean
//    public AcceptHeaderLocaleResolver localeResolver() {
//        return new AcceptHeaderLocaleResolver();
//    }
//
//    /**
//     * 유저 세션에 사전 정의된 속성에 따라 로케일을 해석한다. 세션 속성이 없으면 accept-language 헤더로 기본 로케일을 결정한다.
//     * 로케일이 저장된 세션 속성을 변경함으로써 유저로케일을 변경한다.
//     */
//    @Bean
//    public SessionLocaleResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("en"));
//        return localeResolver;
//    }

}
