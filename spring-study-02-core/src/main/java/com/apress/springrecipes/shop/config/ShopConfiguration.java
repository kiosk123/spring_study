package com.apress.springrecipes.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;

@PropertySource("classpath:discounts.properties")
@ComponentScan("com.apress.springrecipes.shop")
@Configuration
public class ShopConfiguration {
    
    /**
     * properties 파일에서 endofyear.discount의 값을 읽어서 할당
     * endofyear.discount가 없다면 디폴드 값이 0으로 설정됨
     */
    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscountField;
    
    @Bean
    public Product aaa() {
        Battery p1 = new Battery("AAA", 2.5);
        p1.setRechargeable(true);
        return p1;
    }
    
    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5);
        p2.setCapacity(700);
        return p2;
    }
    
    @Bean
    public Product dvdrw() {
        Disc p3 = new Disc("DVD-RW", 3.0);
        p3.setCapacity(700);
        return p3;
    }
    
    /**
     * 다국어 지원을 위한 메시지 리소스 번들 빈 선언
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        /**
         * messages_언어코드_지역코드.properties 파일을 검색 후 선택
         * 위와 같은 형태의 파일이 없을 경우 messages.properties 파일 선택
         * 웹에서는 브라우저를 통해 접속하게 되면 요청헤더의 Content-Language에 ISO언어코드-ISO지역코드 형태로 전송
         */
        messageSource.setBasename("classpath:messages");
        
        /**
         * 1초로 설정하여 쓸모없는 메시지를 다시 읽지 않음
         * 캐시 갱신이 properties 일기전 최종 수정 타임스태프 이후 변경 사항이 있는 지 살표보고 갱신여부 결정
         */
        messageSource.setCacheSeconds(1); 
        return messageSource;
    }
    
    
    /**
     * @PropertySource 사용시 세트로 함께 사용한다.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer 
        propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
