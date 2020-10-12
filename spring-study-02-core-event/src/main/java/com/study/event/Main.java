package com.study.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.ApplicationEvent로 이벤트를 정의한다
 * 쇼핑카드를 체크아웃하면 Cashier빈이 체크아웃 시간이 기록된 CheckoutEvent를 발행한다.
 * 
 * 2. ApplicationEventPublisherAware 이벤트 발생하기 (Cashier)
 * 이벤트를 인스턴스화한 다음 애플리케이션 이벤트 발행기에서 publishEvent() 메서드를 호출하면
 * 이벤트가 발행된다. 이벤트 발행기는 ApplicationEventPublisherAware 인터페이스 구현
 * 클래스에서 가져온다.
 * 
 * 또는 프로퍼티에 @Autowire해서 자동 참조하게 한다.
 * 이때는 ApplicationEventPublisherAware 구현 안해도됨...
 * 
 * 3. ApplicationListener 이벤트 리스닝하기
 * ApplicationListener 구현한 애플리케이션 컨텍스트에 정의된 빈은 타입 변수에 매치되는
 * 이벤트를 모두 알림받는다. 이런 식으로  ApplicationContextEvent같은 특정 그룹의
 * 이벤트들을 리스닝 한다. 4.2부터는 ApplicationListenr 인터페이스 없이 @EventListener만 붙여도
 * 이벤트 리스너로 만들수 있다.
 * 
 * 4. 전체 이벤트를 리스닝할 리스너를 등록한다.
 * 등록절차는 리스너의 빈인스턴스를 선언하거나 컴포너트 스캐닝으로 감지한다.
 * ApplicationListener 인터페이스를 구현한 빈과 @EventListener를 붙인 
 * 메서드가 위치한 빈을 인지하여 이들이 관심있는 이벤트를 각각 통지한다.
 */

public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        Product dvdrw = context.getBean("dvdrw", Product.class);

        ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
        cart1.addItem(aaa);
        cart1.addItem(cdrw);
        System.out.println("Shopping cart 1 contains " + cart1.getItems());

        ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

        Cashier cashier = context.getBean("cashier", Cashier.class);
        cashier.checkout(cart1);
        cashier.checkout(cart2);
    }
}
