package org.sinhan.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

@Slf4j
public class Ex2Application {
    public static void main(String[] args) {
        //spring boot 애플리케이션을 실행하는 메인 메소드
        SpringApplication.run(HelloWorldApplication.class, args);
        //그냥...로그 찍기용
        log.info("test용 로그~~~~~~");
    }
}
