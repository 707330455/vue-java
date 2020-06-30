package com.example.demo.websocket.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Random random = new Random();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        System.out.println("onOpen:有新的连接，总数" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("onClose:有新的断开，总数" + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("onMessage:" + message);
        send("onMessage:" + random.nextInt(100) + ":" + message);
    }

    public void send(String message) {
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
