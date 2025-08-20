package org.zerock.board.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void init(){
        IntStream.rangeClosed(0, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@zerock.org")
                    .build();

            Board board = Board.builder()
                    .title("Title " + i)
                    .content("Content " + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Transactional //해당 method를 한 트랜잭션으로 묶어준다.
    @Test
    void testRead1() throws Exception {
        //given

        //when
        Optional<Board> result = boardRepository.findById(100L); //데이터베이스에 존재하지 않는 번호

        Board board = result.get();
        //then
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    void testReadWithWriter() throws Exception {
        //given

        //when
        Object result = boardRepository.getBoardWithWriter(100L);
        Object[] arr = (Object[]) result; //Object[]로 변환

        //then
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void testGetBoardWithReply() throws Exception {
        //given

        //when
        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        //then
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    void testWithReplyCount() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        //when
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        //then
        result.get().forEach(row -> {
            Object[] arr = row;
            System.out.println(Arrays.toString(arr));
            System.out.println("===================================");
        });

    }
}