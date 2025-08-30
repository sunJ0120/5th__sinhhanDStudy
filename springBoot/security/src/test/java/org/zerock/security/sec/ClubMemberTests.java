package org.zerock.security.sec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.security.entity.ClubMember;
import org.zerock.security.entity.ClubMemberRole;
import org.zerock.security.repository.ClubRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {
    @Autowired
    private ClubRepository repository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*
    1. 1 - 80까지는 USER만 지정
    2. 81 - 90까지는 USER, MANAGER
    3. 91 - 100까지는 USER, MANAGER, ADMIN
     */
    @Test
    void insertDummies() throws Exception {
        //given
        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@zerock.org")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            //default role
            clubMember.addMemberRole(ClubMemberRole.USER);
            if(i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if(i > 90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            repository.save(clubMember);
        });

        //when

        //then

    }

    @Test
    void testRoad() throws Exception {
        //given

        //when
        Optional<ClubMember> result = repository.findByEmail("user95@zerock.org", false);
        ClubMember clubMember = result.get();

        //then
        //ClubMember(email=user95@zerock.org, password=$2a$10$8g7EYrExhaHTYuq1cynMmuB4YBUfGRnEI4e./QKQeQuFb6jBGE..G, name=사용자95, fromSocial=false, roleSet=[ADMIN, MANAGER, USER])
        System.out.println(clubMember);
    }
}
