package com.apress.springrecipes.shop.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Cashier;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.factory.DiscountFactoryBean;
import com.apress.springrecipes.shop.factory.ProductCreator;
import com.apress.springrecipes.shop.factory.ProductCreatorFactory;

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
    
    @Bean
    public ProductCreatorFactory productCreatorFactory() {
        ProductCreatorFactory factory = new ProductCreatorFactory();
        Map<String, Product> products = new HashMap<>();
        products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Disc("CD-RW", 1.5));
        products.put("dvdrw", new Disc("DVD-RW", 3.0));
        factory.setProducts(products);
        return factory;
    }
    
    @Bean("discountFactoryBeanAAA")
    public DiscountFactoryBean DiscountFactoryBeanAAA() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(aaa());
        factory.setDiscount(0.2);
        return factory;
    }
    
    @Bean("discountFactoryBeanCDRW")
    public DiscountFactoryBean DiscountFactoryBeanCDRW() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(cdrw());
        factory.setDiscount(0.1);
        return factory;
    }
    
    @Bean("discountFactoryBeanDVDRW")
    public DiscountFactoryBean DiscountFactoryBeanDVDRW() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(dvdrw());
        factory.setDiscount(0.1);
        return factory;
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
     * initMethod = @PostConstruct
     * destroyMethod = @PreDestory
     */
    @Bean(name = "cashier2", initMethod = "openFile", destroyMethod = "closeFile")
    @Lazy
    public Cashier cashier() {
        String path = System.getProperty("java.io.tmpdir") + "/cashier";
        Cashier c1 = new Cashier();
        c1.setFileName("checkout");
        c1.setPath(path);
        return c1;
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
