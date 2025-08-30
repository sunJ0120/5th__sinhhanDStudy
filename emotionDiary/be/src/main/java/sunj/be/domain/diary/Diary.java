package sunj.be.domain.diary;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import sunj.be.domain.emotion.Emotion;
import sunj.be.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
@Builder(access = AccessLevel.PRIVATE) // 외부에서 builder 막는다.
@Getter
@Log4j2
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 프록시/리플렉션용으로 PROTECTED로 강제
@AllArgsConstructor(access = AccessLevel.PRIVATE) //정적 팩토리 메서드 사용을 강제
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) //name = "user_id"로 joinColumn을 명시한다.
    private User user;

    @Column(nullable = false) //DB 제약조건
    private LocalDateTime date; //작성일과 시간

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Emotion emotion; //감정

    @Column(nullable = false, length = 100)
    private String content; //일기 내용

    /*
    정적 팩토리 메서드
     */
    public static Diary create(User user, LocalDateTime date, Emotion emotion, String content) {
        return Diary.builder()
                .user(user)
                .date(date)
                .emotion(emotion)
                .content(content)
                .build();
    }
}
