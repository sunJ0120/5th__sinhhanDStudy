package sunj.be.domain.diary;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sunj.be.dto.DiaryDto;
import sunj.be.dto.QDiaryDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static sunj.be.domain.diary.QDiary.diary; //static import

/*
DiaryQueryRepository 구현체 - DB I/O를 담당한다.
DiaryQueryRepositoryImpl를 이용해서 구현체를 생성한다.
 */
@Repository
@RequiredArgsConstructor //생성자 주입
public class DiaryQueryRepositoryImpl implements DiaryQueryRepository {

    private final JPAQueryFactory query; //config 파일을 통해 바로 주입

    @Override
    public List<DiaryDto> findAllByUserId(Long userId) {
        return query
                .select(
                        new QDiaryDto(
                        diary.id,
                        diary.createdAt,
                        diary.emotion.stringValue(),
                        diary.content
                ))
                .from(diary)
                .where(diary.user.id.eq(userId))
                .orderBy(diary.createdAt.desc())
                .fetch();
    }

    public Optional<DiaryDto> findByIdAndUserId(Long id, Long userId) {
        DiaryDto result = query
            .select(new QDiaryDto(
                    diary.id,
                    diary.createdAt,
                    diary.emotion.stringValue(),
                    diary.content
            ))
            .from(diary)
            .where(diary.id.eq(id), diary.user.id.eq(userId))
            .fetchOne();

        return Optional.ofNullable(result); //fetchOne()의 경우, 결과가 없으면 null 이므로 이렇게 한 번 감싸서 안전하게 처리한다.
    }
}
