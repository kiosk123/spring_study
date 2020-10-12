package com.study.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 3. ApplicationListener 이벤트 리스닝하기
 * ApplicationListener 구현한 애플리케이션 컨텍스트에 정의된 빈은 타입 변수에 매치되는
 * 이벤트를 모두 알림받는다. 이런 식으로  ApplicationContextEvent같은 특정 그룹의
 * 이벤트들을 리스닝 한다. 4.2부터는 ApplicationListenr 인터페이스 없이 @EventListener만 붙여도
 * 이벤트 리스너로 만들수 있다.
 */
@Component
public class CheckoutListener implements ApplicationListener<CheckoutEvent> {

    //@EventListener
    @Override
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.println("Checkout event [" + event.getTime() + "]");
    }
    
}
