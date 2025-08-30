package org.zerock.security.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
시큐리티 관련 설정하는 클래스이다.
 */
@Configuration //설정 파일이라고 지정하는 것이다.
@EnableWebSecurity //웹 시큐티리 설정을 활성화한다.
@Log4j2
public class SecurityConfig {
    //이건 패스워드 인코더..이다!
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //메모리상에 정해진 인증 정보를 이용할 때 사용한다.
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("1111"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    // antMatchers > requestMatchers
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth
                    .requestMatchers("/sample/all").permitAll()
                    .requestMatchers("/sample/member").hasRole("USER"); // (인가) - 허가하는 것과 관련이 있다.
        });

        //login 페이지는 일단 빈 껍데기로 둔다.
        http.formLogin(loginForm -> {});
        http.csrf(csrf -> csrf.disable()); //csrf 토큰을 발행하지 않도록 설정한다.

        //로그아웃 : csrf 토큰을 사용핳 시, post로만 접근이 가능하다.
        http.logout(logout -> {

        });

        return http.build();
    }
}
