package com.study.mvc;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// 커스텀 핸들러 작성시 TextWebSocketHandler나 BinaryWebSocketHandler를 상속하여 구현한다
public class EchoHandler extends TextWebSocketHandler {
    
    //에러발생시 호출
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    //핸들러의 부분 메시지 지원여부, true면 웹소켓 메시지를 여러번 호출해서 받아볼수 있다.
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    //웹소켓이 열리고 사용할 준비가 되면 호출됨
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
    }

    //웹소켓 접속이 닫힌 후 호출됨
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION CLOSED"));
    }

    //웹소켓에 클라이언트 메시지가 도착하면 호출됨
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        session.sendMessage(new TextMessage("RECEIVED: " + msg));
    }
}
