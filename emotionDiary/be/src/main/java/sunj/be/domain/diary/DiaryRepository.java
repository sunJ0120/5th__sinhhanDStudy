package sunj.be.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryQueryRepository {
    //다이어리 관련 CRUD 자동으로 제공
    long deleteByIdAndUser_Id(Long diaryId, Long userId);
}
