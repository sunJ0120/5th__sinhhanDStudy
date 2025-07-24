package org.sinhan.helloworld.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; // 메모 번호

    //nullable = false를 이용해서 필수 관계로 만든다.
    @Column(length = 200, nullable = false)
    private String memoText; // 메모 내용
}
