package com.study.mvc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EchoHandler {
    
    /**
     * /app/echo로 받은 메시지를 (WebSocketConfiguration에 /app이라는 엔드포인트 프리픽스 설정)
     * /topic/echo 토픽에 넣는다
     * 
     * 사용가능한 메서드 인수와 애너테이션
     * Message 헤더와 본문이 포함된 실제 하부 메시지
     * @Payload 메시지페이로드(기본), 인수에 @Validated를 붙이면 검증 로직이 적용된다.
     * @Header Message에서 주어진 헤더를 얻는다
     * @Headers 전체 메시지 헤더를 Map인수에 넣는다.
     * @MessageHeaders 전체 Message 헤더
     * Principal 현재 유저(설정됐을 겅우에만)
     */
    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public String echo(String msg) {
        return "RECEIVED: " + msg;
    }
}
