package sunj.redisserver.repository;

import sunj.redisserver.dto.MemberCachingDto;

public interface MemberQueryRepository{
    MemberCachingDto findDtoByMemberId(Long memberId);
}
