package sunj.redisserver.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sunj.redisserver.domain.QMember;
import sunj.redisserver.dto.MemberCachingDto;
import sunj.redisserver.dto.QMemberCachingDto;

import static sunj.redisserver.domain.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository{

    private final JPAQueryFactory query;

    public MemberCachingDto findDtoByMemberId(Long memberId){
        return query.select(
                new QMemberCachingDto(
                        member.name,
                        member.creditLimit
                ))
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }
}
