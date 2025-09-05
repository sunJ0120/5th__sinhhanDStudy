package sunj.redisserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunj.redisserver.dto.MemberCachingDto;
import sunj.redisserver.infra.CacheSpec;
import sunj.redisserver.infra.CacheStore;
import sunj.redisserver.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본은 조회 트랜잭션
public class MemberService {
    private final MemberRepository memberRepository;
    private final CacheStore cacheStore;

    public int getBalance(Long memberId) {
        return cacheStore.getOrLoad(
                CacheSpec.BALANCE,
                memberId,
                Integer.class,
                // 캐시 미스일 때만 실행되는 DB 로딩 함수이다. 이를 Supplier를 사용하면 필요한 함수를 정의하면 된다.
                () -> memberRepository.findBalanceByMemberId(memberId)
        );
    }

    public String getHobby(Long memberId) {
        return cacheStore.getOrLoad(
                CacheSpec.HOBBIES,
                memberId,
                String.class,
                () -> memberRepository.findHobbyByMemberId(memberId)
        );
    }

    public MemberCachingDto getSnapshot(Long memberId) {
        return cacheStore.getOrLoad(
                CacheSpec.SNAPSHOT,
                memberId,
                MemberCachingDto.class,
                () -> memberRepository.findDtoByMemberId(memberId)
        );
    }
}
