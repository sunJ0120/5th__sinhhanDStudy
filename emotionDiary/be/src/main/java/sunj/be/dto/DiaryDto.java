package sunj.be.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    record
    1. 기본적으로 모든 필드가 final이고 Canonical Constructor(모든 필드 받는 생성자)만 존재한다.
    2. 불변 객체 처리된다. > dto는 주로 읽기 전용 용도라 잘 맞는다.
    3. 엔티티와 달리 비즈니스 로직이 전혀 없는, “데이터 묶음”이라는 의도가 바로 드러난다.
 */
public record DiaryDto(Long id,
                        LocalDateTime createdAt,
                       String emotion,
                       String content) {
    @QueryProjection
    public DiaryDto{}
}
