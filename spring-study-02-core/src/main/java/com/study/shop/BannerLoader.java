package com.study.shop;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @PostConstruct
 * 빈 생성 후 특정 메소드를 호출하는 데 사용
 *
 */
@Component
public class BannerLoader {
    
    /**
     *  classpath: 프로젝트 폴더의 classpath 기준으로 경로 탐색 (default)
     *  file: 파일의 절대경로를 기준으로 경로 탐색
     *  http: http url을 기준으로 경로 탐색
     */
    @Value("classpath:banner.txt")
    private Resource banner;

    public void setBanner(Resource banner) {
        this.banner = banner;
    }
    
    @PostConstruct
    public void showBanner() throws IOException {
        Files.lines(Paths.get(banner.getURI()), Charset.forName("UTF-8"))
             .forEachOrdered(System.out::println);
    }
    
}
