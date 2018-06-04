package com.robert.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class WebSocketEndPoint extends TextWebSocketHandler {
    private static final Log log = LogFactory.getLog(WebSocketEndPoint.class);

    private static Map<String, WebSocketSession> users = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info("收到信息:" + message.getPayload());

        String sender = (String) session.getAttributes().get("name");
        sendMessageToAll(session, sender + "：" + message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        String name = (String) session.getAttributes().get("name");

        if (name != null) {
            users.put(name, session);
            sendMessageToAll(session, name + "进入");
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String sender = (String) session.getAttributes().get("name");

        if (sender != null) {
            sendMessageToAll(session, sender + "离开");
            users.remove(sender);
        }
    }

    private void sendMessageToAll(WebSocketSession session, String msg) throws IOException {
        TextMessage returnMessage = new TextMessage(msg);

        String sender = (String) session.getAttributes().get("name");
        Set<String> keys = users.keySet();

        for (String name : keys) {
            WebSocketSession ss = users.get(name);

            if (!name.equalsIgnoreCase(sender)) {
                if (ss.isOpen()) {
                    ss.sendMessage(returnMessage);
                }
            }
        }

    }

}
