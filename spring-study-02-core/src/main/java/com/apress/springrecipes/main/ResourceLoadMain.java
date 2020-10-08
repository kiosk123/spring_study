package com.apress.springrecipes.main;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 직접 가져올 리소스를 선언해서 읽어오기
 */
public class ResourceLoadMain {

    public static void main(String[] args) throws IOException {
        /**
         * FileSystemResource, UrlResource, ClassPathResource
         * 사용가능
         */
        Resource resource = new ClassPathResource("discounts.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        
        System.out.println(props);
    }

}
