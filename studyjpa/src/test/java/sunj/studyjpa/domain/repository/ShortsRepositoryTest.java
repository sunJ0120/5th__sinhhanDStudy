package sunj.studyjpa.domain.repository;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sunj.studyjpa.domain.dto.ShortsResponseDto;
import sunj.studyjpa.domain.entity.Customer;
import sunj.studyjpa.domain.entity.Shorts;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortsRepositoryTest {

    @Autowired
    private ShortsRepository shortsRepository;
    @Autowired CustomerRepository customerRepository;

    //test용 객체
    private Customer customer;
    private Shorts shorts;

    @BeforeEach
    void init(){
        customer = Customer.builder()
                .birth(LocalDate.parse("2000-01-20"))
                .account("222222222")
                .accountName("로또1등 당첨기원")
                .point(1000000)
                .pwd("1234")
                .name("오선정")
                .score(950)
                .isBlocked(false)
                .nickname("피용히")
                .userId("sspure123")
                .profileImg("우하하")
                .introduce("폭군의 블로거가 되었습니다.")
                .build();

        // Shorts 생성 + 연관관계 지정(주인 쪽)
        shorts = Shorts.builder()
                .shortsName("다이소에서 5000원에 뽕뽑기")
                .videoName("video_001.mp4")
                .shortsExplain("다이소에서 5000원에 뽕 뽑는 방법이 궁금하다면 드루와")
                .thumbnail("thumb_001.png")
                .build();
    }

    @Test
    void testFindShortsByCustomer() throws Exception {
        //given
        Customer savedCustomer = customerRepository.save(customer);

        shorts.addCustomer(savedCustomer); //편의 메서드로 한 번에 연관관계 지정
        shortsRepository.save(shorts); //기본적으로 있는 save 메서드 사용

        //when - 메서드 쿼리 자동 생성으로 생성되는 메서드 테스트
        List<Shorts> found = shortsRepository.findByCustomerId(savedCustomer.getId());

        //then
        assertEquals(1, found.size());
        assertEquals("다이소에서 5000원에 뽕뽑기", found.get(0).getShortsName());

        System.out.println(found);
    }

    @Test
    void testSearchShorts() throws Exception {
        //given

        //when
        List<ShortsResponseDto> found = shortsRepository.searchShorts("피용히", "다이소");

        //then
        Assertions.assertEquals(found.size(), 1); //하나의 검색 결과가 나올 것이다.
        System.out.println(found);
    }
}