package com.study.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 4. 전체 이벤트를 리스닝할 리스너를 등록한다.
 * 등록절차는 리스너의 빈인스턴스를 선언하거나 컴포너트 스캐닝으로 감지한다.
 * ApplicationListener 인터페이스를 구현한 빈과 @EventListener를 붙인 
 * 메서드가 위치한 빈을 인지하여 이들이 관심있는 이벤트를 각각 통지한다.
 *
 */
@Configuration
@ComponentScan("com.study.event")
public class ShopConfiguration {

    @Bean
    public Product aaa() {
        Battery p1 = new Battery();
        p1.setName("AAA");
        p1.setPrice(2.5);
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
        Disc p2 = new Disc("DVD-RW", 3.0);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Cashier cashier() {
        return new Cashier();
    }
}
