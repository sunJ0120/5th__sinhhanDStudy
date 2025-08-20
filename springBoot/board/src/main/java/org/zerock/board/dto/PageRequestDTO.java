package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
public class PageRequestDTO {
    private int page; // 현재 페이지 번호
    private int size; // 페이지당 항목 수

    public PageRequestDTO() {
        this.page = 1; // 기본값: 첫 페이지, 1로 초기화를 시키기 위해 생성자를 사용한다.
        this.size = 10; // 기본값: 페이지당 10개 항목
    }

    //PageRequest.of(page, size, sort)의 page는 0부터 이기 때문에, -1을 해서 맞춰준다.
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}