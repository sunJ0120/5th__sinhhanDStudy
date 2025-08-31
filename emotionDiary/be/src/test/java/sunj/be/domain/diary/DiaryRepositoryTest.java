package sunj.be.domain.diary;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sunj.be.domain.emotion.Emotion;
import sunj.be.domain.user.User;
import sunj.be.domain.user.UserRepository;
import sunj.be.dto.DiaryDto;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class DiaryRepositoryTest {
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    UserRepository userRepository;

    private Long userId;
    private Long diaryId;

    @BeforeEach
    public void init(){
        //User 생성
        User user = User.create("test", "test", "test");
        //Diary 생성
        user.writeDiary("오늘 기분 좋은!", Emotion.GOOD); //연관관계 편의 메서드로 저장
        userRepository.save(user); //영속 컨텍스트에 반영 및 DB에 플러시
        this.userId = user.getId(); //save 후에는 userId가 채워 지므로 가능하다.
        this.diaryId = user.getDiaryList().get(0).getId();
    }

    @Test
    void findAllByUserIdTest() throws Exception {
        //given

        //when
        List<DiaryDto> dtos = diaryRepository.findAllByUserId(userId);

        //then
        Assertions.assertEquals(1, dtos.size());
        Assertions.assertEquals("GOOD", dtos.get(0).emotion());
    }

    @Test
    void findByIdAndUserIdTest() throws Exception {
        //given

        //when
        Optional<DiaryDto> dto = diaryRepository.findByIdAndUserId(diaryId,userId);

        //then
        Assertions.assertEquals("GOOD", dto.get().emotion());
    }
}