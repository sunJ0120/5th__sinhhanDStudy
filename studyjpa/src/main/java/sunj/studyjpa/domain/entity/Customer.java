package sunj.studyjpa.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Builder
@Getter
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    예제에서는 단방향 매핑으로 진행할꺼라 이 컬렉션은 필요가 없다.
//    private List<Payments> paymentsList = new ArrayList<>();

    //이번 예제에서는 customer와 shorts를 양방향 매핑으로 구현한다.
    @OneToMany(mappedBy = "customer")
    @Builder.Default                // 빌더가 기본값을 유지하도록
    private List<Shorts> shortsList = new ArrayList<>();  // 컬렉션 즉시 초기화

    @Column(nullable = false, length = 10)
    String userId;

    @Column(nullable = false)
    String pwd;

    @Column(nullable = false, length = 10)
    String name;

    @Column(nullable = false)
    LocalDate birth;

    @Column(nullable = false)
    String account;

    @Column(nullable = false)
    String accountName;

    int point;

    @Column(nullable = false)
    boolean isBlocked;

    @Column(nullable = false)
    String profileImg;

    @Column(nullable = false)
    String nickname;

    String introduce;

    @Column(nullable = false)
    int score;

    //신용등급 여기서는 예제에서 배제할 것이므로 일단 주석 처리
//    @ManyToOne(fetch = FetchType.LAZY)
//    Grade grage = new grade();
}
