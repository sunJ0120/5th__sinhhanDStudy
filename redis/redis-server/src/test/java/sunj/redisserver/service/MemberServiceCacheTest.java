package sunj.redisserver.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sunj.redisserver.repository.MemberRepository;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*
현재 우리는 docker container 위에 redis가 있으므로, docker를 자동으로 실행해서 test를 진행하도록 하자.

캐싱 기능 & 캐시 히트 및 미스가 정상적으로 동작하는 것을 볼 수 있다.
*/
@Testcontainers
@SpringBootTest
class MemberServiceCacheTest {

    private static final String REDIS_PASSWORD = System.getenv().get("REDIS_PASSWORD");

    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7")
            .withExposedPorts(6379)
            .withCommand("redis-server", "--requirepass", REDIS_PASSWORD);

    @DynamicPropertySource
    static void redisProps(DynamicPropertyRegistry r) {
        r.add("spring.data.redis.host", () -> redis.getHost());
        r.add("spring.data.redis.port", () -> redis.getMappedPort(6379));
        r.add("spring.data.redis.password", () -> REDIS_PASSWORD);
    }

    @Autowired
    MemberService memberService;

    @MockitoBean
    MemberRepository memberRepository; // DB 대신 목

    @Autowired
    StringRedisTemplate rt;

    @BeforeEach
    void flush() {
        rt.getConnectionFactory().getConnection().serverCommands().flushDb();
    }

    @Test
    void redis_actually_stores_cache() {
        Long id = 1L;
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(7000);

        // Redis가 비어있는지 확인
        Set<String> keysBefore = rt.keys("*");
        System.out.println("Redis keys before: " + keysBefore);

        // 첫 번째 호출 - 캐시 저장됨
        int balance = memberService.getBalance(id);
        assertThat(balance).isEqualTo(7000);

        // Redis에 키가 생겼는지 확인
        Set<String> keysAfter = rt.keys("*");
        System.out.println("Redis keys after: " + keysAfter);

        // 저장된 값들 출력해보기
        for (String key : keysAfter) {
            String value = rt.opsForValue().get(key);
            Long ttl = rt.getExpire(key);
            System.out.println("Key: " + key + ", Value: " + value + ", TTL: " + ttl + " seconds");
        }

        // Repository는 1번만 호출됨
        Mockito.verify(memberRepository, Mockito.times(1)).findBalanceByMemberId(id);
    }

    @Test
    void redis_stores_different_cache_types() {
        Long id = 1L;

        // Repository Mock 설정
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(5000);
        Mockito.when(memberRepository.findHobbyByMemberId(id)).thenReturn("축구");

        System.out.println("=== 캐시 저장 전 ===");
        Set<String> keysBefore = rt.keys("*");
        System.out.println("Redis keys: " + keysBefore);

        // 여러 종류 캐시 저장
        int balance = memberService.getBalance(id);
        String hobby = memberService.getHobby(id);

        System.out.println("=== 캐시 저장 후 ===");
        System.out.println("Balance: " + balance + ", Hobby: " + hobby);

        Set<String> keysAfter = rt.keys("*");
        System.out.println("Redis keys: " + keysAfter);

        // 모든 저장된 데이터 출력
        for (String key : keysAfter) {
            String value = rt.opsForValue().get(key);
            Long ttl = rt.getExpire(key);
            System.out.println("📦 Key: " + key);
            System.out.println("   Value: " + value);
            System.out.println("   TTL: " + ttl + " seconds");
            System.out.println("   Type: " + rt.type(key));
        }

        assertThat(keysAfter.size()).isGreaterThanOrEqualTo(2); // balance, hobby 최소 2개
    }

    @Test
    void redis_cache_hit_verification() {
        Long id = 1L;
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(9999);

        System.out.println("=== 첫 번째 호출 (캐시 미스) ===");
        int result1 = memberService.getBalance(id);
        Set<String> keys1 = rt.keys("*");
        System.out.println("Result: " + result1);
        System.out.println("Redis keys: " + keys1);

        // Repository Mock을 다른 값으로 변경 (캐시 히트면 이 값이 안나와야 함)
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(1111);

        System.out.println("=== 두 번째 호출 (캐시 히트) ===");
        int result2 = memberService.getBalance(id);
        Set<String> keys2 = rt.keys("*");
        System.out.println("Result: " + result2);
        System.out.println("Redis keys: " + keys2);

        // 캐시에서 가져온 값이므로 첫 번째와 같아야 함 (9999)
        assertThat(result2).isEqualTo(9999);
        assertThat(result2).isNotEqualTo(1111); // Mock의 새 값이 아님

        // Repository는 1번만 호출됨 (캐시 히트)
        Mockito.verify(memberRepository, Mockito.times(1)).findBalanceByMemberId(id);

        System.out.println("✅ 캐시 히트 성공! Repository는 " +
                Mockito.mockingDetails(memberRepository).getInvocations().size() + "번만 호출됨");
    }
}