package org.zerock.board.dto;

import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writerEmail; // 작성자의 이메일
    private String writerName; // 작성자의 이름
    private String regDate;
    private String modDate; // 수정일자
    private int replyCount; // 댓글 수
}
