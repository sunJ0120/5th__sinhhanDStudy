package org.sunj.sinhandstudy.WebSocket.Controller;

/*
Endpoint를 명시한다 -> 이게 Controller의 역할
모든 세션을 SET으로 모아서 clients로 저장하도록 한다.
@OnOpen, @OnClose, @OnMessage, @OnError를 지정한다.
 */

import lombok.extern.log4j.Log4j2;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@ServerEndpoint("/chatSocket")
public class ChatSocket {
    //모든 세션을 저장할 집합
    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session){
        clients.add(session);
        log.info("새 연결 : {}",session.getId());
    }

    @OnMessage
    public void onMessage(String msg, Session sender) throws IOException {
        log.info("메세지 수신 : {}", msg);
        //연결된 다른 모든 세션에게 메세지 전달
        synchronized (clients){
            for(Session client : clients){
                if(client != null && !client.equals(sender)){
                    client.getBasicRemote().sendText(msg);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        clients.remove(session);
        log.info("연결 종료 : {}", session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        log.error("에러 발생 : {}", throwable.getMessage());
    }
}
