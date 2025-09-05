package sunj.redisserver.domain;

/*
REDIS TEST용 ENTITY
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId; //id라고 하지 말고, 우리 프로젝트 형식에 완전 맞춰서 진행해보자.

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String hobby;

    @Column(nullable = false)
    private int creditLimit;

    @Column(nullable = false)
    private int balance; //잔액
}
