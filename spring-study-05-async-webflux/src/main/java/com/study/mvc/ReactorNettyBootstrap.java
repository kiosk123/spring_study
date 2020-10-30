package com.study.mvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.ipc.netty.http.server.HttpServer;

/**
 * 메인 메서드를 실행해서 돌려도 되고
 * 웹컨테이너에 넣어서 프로그램을 돌려도된다.
 *
 */
public class ReactorNettyBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfiguration.class);
        HttpHandler handler =
                WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        HttpServer.create("localhost", 8090)
                .newHandler(adapter).block();
    }
}
