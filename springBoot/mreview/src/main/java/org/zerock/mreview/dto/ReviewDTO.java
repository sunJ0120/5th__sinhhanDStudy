package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    // review
    private Long reviewnum;
    private Long mno;
    private int grade;
    private String text;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // member
    private Long mid;
    private String nickname;
    private String email;

}
