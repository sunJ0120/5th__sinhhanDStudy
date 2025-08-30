package org.zerock.board.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @BeforeEach
    void init() {
        IntStream.rangeClosed(0, 300).forEach(i -> {
            //1부터 100까지의 랜덤한 숫자를 생성하여 bno로 사용
            long bno = (long) (Math.random() * 100 + 1);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply " + i)
                    .replyer("guest")
                    .board(board)
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    void insertReply() throws Exception {
        //given

        //when
        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();
        //then
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    void testListByBoard() throws Exception {
        //given

        //when
        List<Reply> replyList = replyRepository.getRepiesByBoardOrderByRno(
                Board.builder().bno(97L).build());

        //then
        replyList.forEach(reply -> System.out.println(reply));
    }
}