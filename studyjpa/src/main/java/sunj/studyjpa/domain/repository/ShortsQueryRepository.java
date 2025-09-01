package sunj.studyjpa.domain.repository;

import sunj.studyjpa.domain.dto.ShortsResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ShortsQueryRepository {
    //동적 쿼리 예제 : 특정 조건으로 쇼츠를 검색하는 쿼리를 작성해보자.
    List<ShortsResponseDto> searchShorts(String nickname, String keyword);
}
