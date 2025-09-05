package sunj.redisserver.infra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

/*
REDIS 캐싱을 위한 TTL 설정 값

키 예시 : v1:snap:user:123, v1:bal:user:123, v1:hb:user:123
 */
@Getter
@RequiredArgsConstructor
public enum CacheSpec {
    // 1) 사용자 스냅샷(JSON)
    SNAPSHOT("v1:snap", Duration.ofHours(12)),
    // 2) 잔액(String) - 3분
    BALANCE("v1:bal", Duration.ofMinutes(3)),
    // 3) 취미(List<String> or Set<String>) - 5분
    HOBBIES("v1:hb", Duration.ofMinutes(5));

    private final String prefix; //키 네임 스페이스
    private final Duration ttl;
}
