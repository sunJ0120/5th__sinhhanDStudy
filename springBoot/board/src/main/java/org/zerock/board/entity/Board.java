package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") //@ToString은 항상 exclude를 사용하여 무한 참조를 방지해야 한다.
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; // 게시글 번호

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩을 사용하여 성능 최적화
    private Member writer; // 작성자, 연관관계를 지정
}
