package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") //@ToString은 항상 exclude를 사용하여 무한 참조를 방지해야 한다.
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; // 댓글 번호
    private String text;
    private String replyer; // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; // 연관관계를 지정한다.
}
