package com.apress.springrecipes.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Scope 설정하지 않거나 애너테이션이 없으면 기본은 singleton
 * prototype은 getBean을 호출하거나 @Autowire로 주입시
 * 새로운 인스턴스 생성한다.
 *
 */
@Component
@Scope("prototype")
public class ShoppingCart {
    private List<Product> items = new ArrayList<>();
    
    public void addItem(Product item) {
        items.add(item);
    }
    
    public List<Product> getItems() {
        return items;
    }
}
