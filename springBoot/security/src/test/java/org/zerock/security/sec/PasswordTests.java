package org.zerock.security.sec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testEncode() throws Exception {
        //given
        String password = "1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw : " + enPw);

        //when
        boolean matchResult = passwordEncoder.matches(password, enPw);

        //then
        //encoding 때문인지, 실행 할때마다 달라진다고 한다~
        System.out.println("matchResult : " + matchResult);

    }
}
