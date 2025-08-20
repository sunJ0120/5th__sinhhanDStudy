package org.zerock.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * PageResultDTO는 페이지 결과를 DTO로 변환하는 클래스입니다.
 * EN : Entity Type
 * DTO : Data Transfer Object Type
 *
 * REST API에서는 이렇게 안 했던 걸로 기억하는데..움
 * 이거 REST API 방식이랑 비교해봐야 할듯.......
 */
@Data
public class PageResultDTO<DTO, EN> {
    // DTO 리스트
    private List<DTO> dtoList;
    // 총 페이지 번호
    private int totalPage;
    // 현재 페이지 번호
    private int page;
    // 목록 사이즈
    private int size;
    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;
    // 이전, 다음 페이지
    private boolean prev, next;
    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        // 페이지의 엔티티 목록을 DTO 목록으로 변환
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; // 페이지 번호는 0부터 시작하므로 +1
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9; // 시작 페이지 번호
        end = Math.min(tempEnd, totalPage); // 끝 페이지 번호
        next = totalPage > tempEnd; // 다음 페이지 여부

        pageList = IntStream.range(start, end).boxed().collect(Collectors.toList());
    }
}
