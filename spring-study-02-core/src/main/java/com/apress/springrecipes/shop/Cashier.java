package com.apress.springrecipes.shop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @PostConstruct
 * 빈 생성 후 특정 메소드를 호출하는 데 사용
 * @PreDestory
 * 빈 폐기 전 특정 메소드를 호출하는 데 사용
 * @Lazy
 * 빈을 호출(요청)한 시점에 초기화
 * 스프링 컨텍스트는 초기화시 모든 빈을 생성하지만
 * 초기화 부하가 큰 빈은 생성시점을 나중으로 미뤄
 * 부하를 줄일 수 있음
 */

@Primary
@Component
@Lazy
public class Cashier {
    
    @Autowired
    private MessageSource messageSource;
    
    @Value("checkout")
    private String fileName;
    
    @Value("C:\\Users\\HEOJON~1\\AppData\\Local\\Temp\\cashier")
    private String path;
    
    private BufferedWriter writer;
    
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    @PostConstruct
    public void openFile() throws IOException {
        System.out.println("====== Casher Load ======");
        File targetDir = new File(path);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        
        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }
        
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
        String alert = messageSource.getMessage("alert.inventory.checkout",
                new Object[] {cart.getItems(), new Date()}, Locale.US);
        System.out.println(alert);
        writer.write(alert);
        writer.flush();
    }
    
    @PreDestroy
    public void closeFile() throws IOException {
        writer.close();
    }
}
