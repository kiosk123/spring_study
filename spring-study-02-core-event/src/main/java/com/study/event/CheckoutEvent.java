package com.study.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

/**
 * 1.ApplicationEvent로 이벤트를 정의한다
 * 쇼핑카드를 체크아웃하면 Cashier빈이 체크아웃 시간이 기록된 CheckoutEvent를 발행한다.
 */
public class CheckoutEvent extends ApplicationEvent {

    private final ShoppingCart cart;
    private final Date time;
    
    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
        this.cart = cart;
        this.time = time;
    }
    
    
    public ShoppingCart getCart() {
        return cart;
    }
    
    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "CheckoutEvent [toString()=" + super.toString() + "]";
    }
}
