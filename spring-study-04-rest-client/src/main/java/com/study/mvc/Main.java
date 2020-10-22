package com.study.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {

    public static void main(String[] args) throws Exception {
        String uri = "http://localhost:8080/court/members.xml";
        RestTemplate restTemplate = new RestTemplate();
        
        //url에 대한 GET 요청 결과를 String 타입으로 반환
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        
        //@PathVariable 처럼 사용하는 방법
        uri = "http://localhost:8080/court/member/{memberId}";
        Map<String, String> params = new HashMap<>();
        params.put("memberId", "1");
        restTemplate = new RestTemplate();
        result = restTemplate.getForObject(uri, String.class, params);
        System.out.println(result);
        
        //데이터를 매핑된 객체로 가져오기
        uri = "http://localhost:8080/court/members.xml";
        restTemplate = new RestTemplate();
        Member member = restTemplate.getForObject(uri, Member.class, params);
        //restTemplate.getForEntity(url, T) //ResponseEntity<T> 로 받는 방식
        System.out.println(member);
        
        //naver open api 
        uri = "https://openapi.naver.com/v1/search/news?query={query}";
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "VZQMzuvJfnRx9MVT0tC6");
        headers.set("X-Naver-Client-Secret", "JJ1r_9X0Ez");
        
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response= restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, "조용필");
//        restTemplate.exchange(uri, HttpMethod.GET, entity, Map.class);        
        System.out.println(response.getBody());
    }
}
