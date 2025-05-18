package org.sunj.simpleomok.controller;

import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/omokSocket")
@Log4j2
public class OmokSocket {
    //모든 세션(사용자)를 저장할 공간이 필요하다.
    private static final Set<Session> allClients = Collections.synchronizedSet(new HashSet<>());
    //클라이언트별로 역할이 있으므로, 역할을 저장하기 위한 매핑도 필요하다.
    private static Map<Session, String> playerRoles = new HashMap<>(); //session → "black", "white", "observer"
    private static String currentTurn = "black"; // ✅ 흑돌부터 시작

    @OnOpen
    public void onOpen(Session session) throws IOException {
        allClients.add(session);
        log.info("새로운 클라이언트 연결 : {}", session.getId()); //클라이언트가 잘 연결 되었는지 세션 아이디 검사

        //클라이언트들에게 역할을 부여하는 과정이 필요하다.
        String role;

        if(playerRoles.size() == 0){ //아직 아무도 안들어옴
            role = "black"; //흑돌
        }else if(playerRoles.size() == 1){ //흑돌 이미 들어옴
            role = "white"; //백돌
        }else{
            role = "observer"; //관전자
        }

        playerRoles.put(session, role); //현재 세션과 부여된 role을 매핑

        JSONObject joinMsg = new JSONObject();
        //다음과 같이 type, role을 만들어서 json으로 전송
        joinMsg.put("type", "playerJoin");
        joinMsg.put("role", role);

        session.getBasicRemote().sendText(joinMsg.toString());
        //json을 만들었으므로, message를 날려준다.
        //이거 나중에 js에서 받아서 playerJoin이면 어떻게 진행...하는 식으로 설정 가능
    }

    //여기서 돌을 두고 broadcast 하는 과정이 필요하다.
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        log.info("메세지 수신 : {}", message);
        String role = playerRoles.get(session);
        //관전자 모드일 경우, 돌을 둘 수 없다.
        if("observer".equals(role)){
            return; //돌을 둘 수 없도록 처리
        }

        //돌두기 시작
        //좌표값이 있는 json을 가져와서 string으로 반환하여 broadcast 한다.
        //여기서 분기 처리를 위해 type을 넣는다.
        JSONObject recieved = new JSONObject(message);
        if(!"putStone".equals(recieved.getString("type"))){
            return; //돌을 두는게 아닐경우 그냥 return
        }

        int x = recieved.getInt("x");
        int y = recieved.getInt("y");
        String color = recieved.getString("color");
        //수 검사
        //1. 현재 턴 검사
        // 🚨 현재 턴 검사
        if (!color.equals(currentTurn)) {
            log.warn("잘못된 차례입니다. 현재 턴: {}, 요청한 색상: {}", currentTurn, color);
            return;
        }

        // 🎯 다음 턴으로 전환
        currentTurn = currentTurn.equals("black") ? "white" : "black";

        //여기서 잘 들어오는지 확인 필요할듯.
        log.info("돌을 둡니다. 좌표 : ({}, {}), 색상 : {}", x, y, color);

        //updateBoard로 보내기
        JSONObject broadcast = new JSONObject();
        broadcast.put("type", "updateBoard");
        broadcast.put("x", x);
        broadcast.put("y", y);
        broadcast.put("color", color);
        broadcast.put("nextTurn", currentTurn); // 🔥 다음 턴 전달!

        synchronized (allClients) {
            for(Session client : allClients){
                if(client != null){ //null이 아닌 모든 client에게 전송
                    client.getBasicRemote().sendText(broadcast.toString());
                }
            }
        }
    }
    //퇴장 처리
    @OnClose
    public void onClose(Session session) throws IOException{
        allClients.remove(session);
        playerRoles.remove(session);
    }
}
