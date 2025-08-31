package sunj.be.domain.diary;

import sunj.be.dto.DiaryDto;

import java.util.List;
import java.util.Optional;

public interface DiaryQueryRepository {
    /*
    List 화면 : 해당 유저의 모든 다이어리를 최신순으로
     */
    List<DiaryDto> findAllByUserId(Long userId);
    /*
    다이어리 페이지 : 해당 유저의 해당 다이어리를 단건으로 조회
     */
    Optional<DiaryDto> findByIdAndUserId(Long id, Long userId);
}
