package com.study.mvc;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
 
//        final String url = "http://localhost:8080/court-rx";
        final String url = "https://openapi.naver.com/v1/search/news";

        WebClient.create(url)
                    .get()
                    .uri(builder -> builder.queryParam("query", "hello").build())
//                    .uri("/reservations/{courtName}", "Tennis")
                    .header("X-Naver-Client-Id", "VZQMzuvJfnRx9MVT0tC6")
                    .header("X-Naver-Client-Secret", "JJ1r_9X0Ez")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .exchange()
                    .flatMapMany(cr -> cr.bodyToFlux(String.class)).subscribe(System.out::println);

        System.in.read();
    }
}
