package com.study.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 
 * 스프링 4버전에서는 AbstractWebSocketMessageBrokerConfigurer를 상속받아 하여 구현
 */
@Configuration
@EnableWebSocketMessageBroker //웹소켓 메시징 애너테이션
@ComponentScan
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //도착지가 /topic으로 시작하는 메시지 중개기
        registry.setApplicationDestinationPrefixes("/app"); //STOMP 중계 엔드포인트 프리픽스에 /app을 붙인다.
        // registry.enableStompBrokerRelay(destinationPrefixes) //실제 엔터프라이즈 중개기(RabbitMQ, ActiveMQ등)에 접속시 사용
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp.js(https://github.com/JSteunou/webstomp-client)라이브러리를 활용하여 클라이언트에서 접속
        // ws://localhost:8080/spring-study-05-async-websocket-stomp/echo-endpoint
        registry.addEndpoint("/echo-endpoint"); //stomp 메시지를 리스닝하는 웹소켓 엔드포인트
    }
}
