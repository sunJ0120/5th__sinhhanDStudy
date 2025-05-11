package org.sunj.sinhandstudy.WebSocket.Controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "chatController", urlPatterns = "/chat")
public class ChatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("chat Get........");
        //사용자의 닉네임을 parameter로 받아온다.
        String nickname = req.getParameter("nickname");
        //닉네임이 없을 경우, 랜덤값
        if(nickname == null || nickname.trim().length() == 0){
            nickname = "방문자" + (int)(Math.random() * 100);
        }
        req.setAttribute("nickname", nickname); //jsp에서 쓰기 위해 필요
        req.getRequestDispatcher("WEB-INF/views/chat.jsp").forward(req, resp);
    }
}
