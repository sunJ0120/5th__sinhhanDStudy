package sunj.be.domain.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import sunj.be.domain.BaseTime;
import sunj.be.domain.diary.Diary;
import sunj.be.domain.emotion.Emotion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Log4j2
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA가 프록시 생성할때 안전하게 생성 가능하다.
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50) //로그인을 위해 유니크 조건
    private String username; //로그인 id

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user",
                cascade = CascadeType.ALL, // PERSIST, MERGE, REMOVE 등 전파 하도록 구성
                orphanRemoval = true) // 컬렉션에서 빠진 Diary는 고아로 간주되어 삭제
    @Builder.Default //컬렉션 필드는 필드 초기화만으로는 안 되고 반드시 @Builder.Default가 필요하다.
    private List<Diary> diaryList = new ArrayList<>();

    /*
    정적 팩토리 메서드

    차후 패스워드 암호화, 기본 닉네임 규칙 등도 추가 가능
     */
    public static User create(String username, String password, String nickname) {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

    // 변경 메서드
    public void changePwd(String encryptedPwd) {
        this.password = encryptedPwd;
    }
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    /*
    연관관계 편의 메서드 작성
    연관관계 편의 메서드는, 양방향일때 연관관계를 순수한 환경에서 설정하기 위해 사용한다.

    - writeDiary : setter 사용을 막아두기 위해 user에 다이어리 생성 기능을 추가한다.
     */
    public Diary writeDiary(String content, Emotion emotion) {
        Diary diary = Diary.create(this, emotion, content);
        this.diaryList.add(diary);
        return diary;
    }
}
