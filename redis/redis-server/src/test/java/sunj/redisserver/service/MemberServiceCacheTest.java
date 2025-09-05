package sunj.redisserver.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sunj.redisserver.dto.MemberCachingDto;
import sunj.redisserver.repository.MemberRepository;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*
현재 우리는 docker container 위에 redis가 있으므로, docker를 자동으로 실행해서 test를 진행하도록 하자.
캐싱 기능 & 캐시 히트 및 미스가 정상적으로 동작하는 것을 볼 수 있다.
*/
@Testcontainers
@SpringBootTest
@Slf4j
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
    RedisTemplate<String, Object> redisTemplate; // 내가 정의한 커스텀 템플릿 이용

    @BeforeEach
    void flush() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushDb();
    }

    @Test
    void redisActuallyStoresCache() {
        Long id = 1L;
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(7000);

        // Redis가 비어있는지 확인
        Set<String> keysBefore = redisTemplate.keys("*");
        log.info("Redis keys before: {}", keysBefore);

        // 첫 번째 호출 - 캐시 저장됨
        int balance = memberService.getBalance(id);
        assertThat(balance).isEqualTo(7000);

        // Redis에 키가 생겼는지 확인
        Set<String> keysAfter = redisTemplate.keys("*");
        log.info("Redis keys after: {}", keysAfter);

        // 저장된 값들 출력해보기
        for (String key : keysAfter) {
            Object value = redisTemplate.opsForValue().get(key);
            Long ttl = redisTemplate.getExpire(key);
            log.info("Key : {}, Value : {}, TTL : {} seconds",key,value,ttl);
        }

        // Repository는 1번만 호출됨 (왜냐면.. 캐싱이 되었기 때문이다.)
        Mockito.verify(memberRepository, Mockito.times(1)).findBalanceByMemberId(id);
    }

    @Test
    void redisStoresDifferentCacheTypes() {
        //test에 사용하기 위한 객체들
        Long id = 1L;
        MemberCachingDto dto = MemberCachingDto.builder()
                .name("피용희")
                .creditLimit(100000)
                .build();
        int balance = 5000;
        String hobby = "축구";

        // Repository Mock 설정
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(balance);
        Mockito.when(memberRepository.findHobbyByMemberId(id)).thenReturn(hobby);
        Mockito.when(memberRepository.findDtoByMemberId(id)).thenReturn(dto);

        System.out.println("=== 캐시 저장 전 ===");
        Set<String> keysBefore = redisTemplate.keys("*");
        log.info("Redis keys: {}",keysBefore);

        // 여러 종류 캐시 저장
        balance = memberService.getBalance(id);
        hobby = memberService.getHobby(id);
        dto = memberService.getSnapshot(id);

        System.out.println("=== 캐시 저장 후 ===");
        log.info("Balance: {}, Hobby: {}, dto : {}",balance,hobby,dto);

        Set<String> keysAfter = redisTemplate.keys("*");
        log.info("Redis keys: {}",keysAfter);

        // 모든 저장된 데이터 출력
        for (String key : keysAfter) {
            Object value = redisTemplate.opsForValue().get(key);
            Long ttl = redisTemplate.getExpire(key);
            log.info("Key: {}",key);
            log.info("Value: {}",value);
            log.info("TTL: {} seconds",ttl);
            log.info("Type: {}",redisTemplate.type(key)); //int, String, dto 나와야 한다.
        }
        assertThat(keysAfter.size()).isGreaterThanOrEqualTo(3); // balance, hobby, dto 최소 3개
    }

    @Test
    void redisCacheHitVerification() {
        Long id = 1L;
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(9999);

        System.out.println("=== 첫 번째 호출 (캐시 미스) ===");
        int result1 = memberService.getBalance(id);
        Set<String> keys1 = redisTemplate.keys("*");
        log.info("Result: {}",result1);
        log.info("Redis keys: {}", keys1);

        // Repository Mock을 다른 값으로 변경 (캐시 히트면 이 값이 안나와야 함...바뀌기 전 값이 와야 하니까)
        Mockito.when(memberRepository.findBalanceByMemberId(id)).thenReturn(1111);

        System.out.println("=== 두 번째 호출 (캐시 히트) ===");
        int result2 = memberService.getBalance(id);
        Set<String> keys2 = redisTemplate.keys("*");
        log.info("Result: {}",result2);
        log.info("Redis keys: {}", keys2);

        // 캐시에서 가져온 값이므로 첫 번째와 같아야 함 (9999)
        assertThat(result2).isEqualTo(9999);
        assertThat(result2).isNotEqualTo(1111); // Mock의 새 값이 아님

        // Repository는 1번만 호출됨 (캐시 히트)
        Mockito.verify(memberRepository, Mockito.times(1)).findBalanceByMemberId(id);

        log.info("캐시 히트 성공! Repository는 {}이고, {}번만 호출",result2,Mockito.mockingDetails(memberRepository).getInvocations().size());
    }
}