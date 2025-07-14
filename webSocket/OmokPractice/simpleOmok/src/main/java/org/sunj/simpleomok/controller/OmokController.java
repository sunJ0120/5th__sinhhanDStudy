package org.sunj.simpleomok.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "omokController", urlPatterns = "/game")
public class OmokController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("omokController doGet...............");
        //사용자의 닉네임과 돌 색상을 파라미터에서 받아온다.
        String nickname = req.getParameter("nickname");

        //닉네임이 없을 경우, 랜덤값으로 준다.
        if(nickname == null || nickname.trim().length() == 0){
            nickname = "플레이어" + (int)(Math.random() * 100);
        }

        req.setAttribute("nickname", nickname); //req에 넣어줘야 view에서 사용가능, model에 넣는 것이다.
        req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req, resp); //game으로 보낸다.
    }
}
