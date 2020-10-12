package com.study.event;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 2. ApplicationEventPublisherAware 이벤트 발생하기 
 * 이벤트를 인스턴스화한 다음 애플리케이션 이벤트 발행기에서 publishEvent() 메서드를 호출하면
 * 이벤트가 발행된다. 이벤트 발행기는 ApplicationEventPublisherAware 인터페이스 구현
 * 클래스에서 가져온다.
 * 
 * 또는 프로퍼티에 @Autowire해서 자동 참조하게 한다.
 * 이때는 ApplicationEventPublisherAware 구현 안해도됨...
 */
public class Cashier implements ApplicationEventPublisherAware {

    // @Autowire
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkout(ShoppingCart cart) throws IOException {
        CheckoutEvent event = new CheckoutEvent(cart, new Date());
        applicationEventPublisher.publishEvent(event);
    }
}
