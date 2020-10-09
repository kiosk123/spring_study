package com.apress.springrecipes.shop.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.factory.ProductCreator;

/**
 * jvm 파라미터로 다음곽 같이 @Profile에 설정된 프로파일 명을 넘겨주면
 * 관련 프로파일이 설정된 빈만 로딩됨
 * -Dspring.profiles.active=summer (프로필이 한개)
 * -Dspring.profiles.active=summer,winter (프로필이 여러개)
 */
@ComponentScan("com.apress.springrecipes.shop")
@Configuration
@Profile({"summer", "winter"}) //한개만 설정할 경우 @Profile("프로파일명")
public class ShopConfigurationSumWin {
    
    @Bean
    public Product aaa() {
        return ProductCreator.createProduct("aaa");
    }
    
    @Bean
    public Product cdrw() {
        return ProductCreator.createProduct("cdrw");
    }
    
    @Bean
    public Product dvdrw() {
        return ProductCreator.createProduct("dvdrw");
    }
}
