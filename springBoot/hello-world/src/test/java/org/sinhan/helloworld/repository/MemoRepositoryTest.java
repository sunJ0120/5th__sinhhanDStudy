package org.sinhan.helloworld.repository;

import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.sinhan.helloworld.entity.Memo;
import org.sinhan.helloworld.entity.QMemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        //memoRepository 로그 테스트 : jdk.proxy3.$Proxy125
        //jpa는 프록시 객체를 사용하여 실제 구현체를 감싸는 방식으로 동작합니다.
        log.info("memoRepository 로그 테스트 : {}", memoRepository.getClass().getName());
    }

    @Test
    public void testSave(){
        // Memo 엔티티를 저장하는 테스트
        // 실제 Memo 엔티티를 생성하고 저장하는 로직을 추가할 수 있습니다.
        // 예시로는 memoRepository.save(new Memo(...)); 와 같은 방식으로 사용합니다.
        log.info("Memo 엔티티 저장 테스트, 1부터 100까지의 샘플 메모를 저장합니다.");
        IntStream.rangeClosed(1,100).forEach(i -> {
            memoRepository.save(Memo.builder()
                    .memoText("Sample Memo...... " + i)
                    .build());
        });
    }

    @Test
    public void testSelect(){
        // Memo 엔티티를 조회하는 테스트
        // 예시로는 memoRepository.findById(1L); 와 같은 방식으로 사용합니다.
        log.info("Memo 엔티티 조회 테스트, ID가 1인 메모를 조회합니다.");
        memoRepository.findById(1L).ifPresent(memo -> {
            log.info("조회된 메모: {}", memo);
        });
    }

    //수정 작업 테스트
    @Test
    public void testUpdate(){
        // Memo 엔티티를 수정하는 테스트
        Memo memo1 = Memo.builder().mno(100L).memoText("Updated Memo Text").build();

        memoRepository.save(memo1);
        log.info("수정된 메모: {}", memo1);
    }

    //삭제 작업 테스트
    @Test
    public void testDelete(){
        // Memo 엔티티를 삭제하는 테스트
        Long memoIdToDelete = 100L; // 삭제할 메모의 ID
        log.info("메모 삭제 테스트, ID가 {}인 메모를 삭제합니다.", memoIdToDelete);

        memoRepository.deleteById(memoIdToDelete);

        // 삭제 후 확인
        if (!memoRepository.existsById(memoIdToDelete)) {
            log.info("메모 ID {}가 성공적으로 삭제되었습니다.", memoIdToDelete);
        } else {
            log.error("메모 ID {} 삭제 실패", memoIdToDelete);
        }
    }

    //페이징 처리 테스트
    @Test
    public void testPageDefault(){
        //1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        log.info("전체 페이지 : {}", result.getTotalPages()); //전체 페이지 : 10
        log.info("페이지 번호: {}", result.getNumber()); //페이지 번호: 0
        log.info("페이지 Count : {}", result.getTotalElements()); //페이지 Count : 99
        log.info("페이지 Size : {}", result.getSize()); //페이지 Size : 10
        log.info("다음 페이지가 있는지? : {}", result.hasNext()); //다음 페이지가 있는지? : true
        log.info("시작 페이지 여부 : {}", result.isFirst()); //시작 페이지 여부 : true

        System.out.println("--------------------------------------------------");

        for(Memo memo : result.getContent()) {
            log.info("Memo: {}", memo);
        }
    }

    //페이징 처리 및 정렬 테스트
    /*
        order by
            m1_0.mno desc
        limit
            ?, ?

         이렇게 limit가 추가된다.
     */
    @Test
    public void testSort(){
        // mno를 기준으로 내림차순 정렬하여 페이징 처리하는 테스트
        Sort sort1 = Sort.by("mno").descending(); // mno를 기준으로 내림차순 정렬
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2); //and를 이용해서 연결한다.

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            log.info("Memo: {}", memo);
        });
    }

    @Test
    public void testQueryMethods(){
        // mno가 70과 80 사이에 있는 메모를 조회하는 테스트
        log.info("mno가 70과 80 사이에 있는 메모를 조회합니다.");
        memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L)
                .forEach(memo -> log.info("Memo: {}", memo));
    }

    @Test
    public void testQueryMethodsWithPageable(){
        // mno가 70과 80 사이에 있는 메모를 페이징 처리하여 조회하는 테스트
        log.info("mno가 70과 80 사이에 있는 메모를 페이징 처리하여 + 내림차순 정렬하여 조회합니다.");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> log.info("Memo: {}", memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    public void testFindMemoText(){
        List<Memo> memos = memoRepository.findByMemoText("3"); //JPQL
        memos.forEach(System.out::println);

        List<Memo> memos2 = memoRepository.findByMemoTextContaining("3"); //쿼리 메서드
        memos2.forEach(System.out::println);
    }

    @Test
    public void querydslTest(){
        //사용자가 전송한 값
        String searchType = "text";
        String searchWord = "3";

        //1. BooleanBuilder 객체 생성
        BooleanBuilder builder = new BooleanBuilder();
        //2. QMemo 객체 생성
        QMemo memo = QMemo.memo;
        //3. 처리(조건)
        if(searchType.equals("text")){
            builder.and(memo.memoText.containsIgnoreCase(searchWord));
        }

        //mno > 0
        builder.and(memo.mno.gt(0));

        //아까 했던 방식....
        //Pageable pageable이 들어가면 무조건 return은 Page type이다.
        Pageable pageable = PageRequest.of(1, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findAll(builder, pageable);
        log.info("total 갯수 : {}", result.getTotalElements());
    }
}