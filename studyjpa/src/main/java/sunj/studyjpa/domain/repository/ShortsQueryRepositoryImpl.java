package sunj.studyjpa.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sunj.studyjpa.domain.dto.QShortsResponseDto;
import sunj.studyjpa.domain.dto.ShortsResponseDto;

import java.time.LocalDate;
import java.util.List;

import static sunj.studyjpa.domain.entity.QShorts.shorts;

@Repository
@RequiredArgsConstructor //생성자 주입
public class ShortsQueryRepositoryImpl implements ShortsQueryRepository {

    private final JPAQueryFactory query; //config 파일을 통해 바로 주입

    @Override
    public List<ShortsResponseDto> searchShorts(String nickname, String keyword) {
        return query
                .select(
                        new QShortsResponseDto(
                        shorts.id,
                        shorts.shortsName,
                        shorts.thumbnail
                ))
                .from(shorts)
                .where(
                        nicknameEq(nickname),
                        keywordContains(keyword)
                ).orderBy(shorts.shortsName.asc())
                .fetch();
    }

    // --- 조건 메서드 (동적 쿼리) ---
    // 조건 메서드끼리 조합해서 재사용도 가능하다. (모듈화가 잘 되어 있음)
    private BooleanExpression nicknameEq(String nickname) {
        return nickname != null ? shorts.customer.nickname.eq(nickname) : null;
    }

    private BooleanExpression keywordContains(String keyword) {
        return keyword != null ? shorts.shortsName.containsIgnoreCase(keyword) : null;
    }
}
