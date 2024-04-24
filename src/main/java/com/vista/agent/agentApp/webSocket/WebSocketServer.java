package com.vista.agent.agentApp.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class WebSocketServer extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));

        Thread.sleep(1000);
        session.sendMessage(new TextMessage("1"));

        Thread.sleep(1000);
        session.sendMessage(new TextMessage("2"));

        Thread.sleep(1000);
        session.sendMessage(new TextMessage("3"));


        session.close(CloseStatus.NORMAL);
    }


}
