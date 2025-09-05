package sunj.studyjpa.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sunj.studyjpa.domain.dto.FilteredShortsResponse;
import sunj.studyjpa.domain.dto.QFilteredShortsResponse;

import java.util.List;

import static sunj.studyjpa.domain.entity.QShorts.shorts;

@Repository
@RequiredArgsConstructor //생성자 주입
public class ShortsQueryRepositoryImpl implements ShortsQueryRepository {

    private final JPAQueryFactory query; //config 파일을 통해 바로 주입

    @Override
    public List<FilteredShortsResponse> searchShorts(String nickname, String keyword) {
        return query
                .select(
                        new QFilteredShortsResponse(
                        shorts.id,
                        shorts.shortsName,
                        shorts.thumbnail
                ))
                .from(shorts)
                .where(
                        nicknameEq(nickname),
                        keywordContains(keyword)
                ).orderBy(shorts.shortsName.desc())
                .fetch();
    }





    private BooleanExpression keywordContains(String keyword) {
        return keyword != null ? shorts.shortsName.containsIgnoreCase(keyword) : null;
    }
}
