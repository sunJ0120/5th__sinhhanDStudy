package org.zerock.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {
    // 비로그인인 경우에도 접속이 가능하다.
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll..............");
    }

    // 로그인 (인증)
    @GetMapping("/member")
    public void exMember(){
        log.info("exMember..............");
    }

    // 로그인 + 관리자 권한 (인증 & 인가)
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin..............");
    }
}
