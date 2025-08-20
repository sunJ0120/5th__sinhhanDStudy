package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;

@SpringBootTest
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    void testRegister() throws Exception {
        //given

        //when
        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test....")
                .writerEmail("user55@aaa.com")
                .build();

        Long bno = boardService.register(dto);

        //then

    }

    @Test
    void testList() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        //when
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        //then
        for(BoardDTO dto : result.getDtoList()) {
            System.out.println(dto);
        }
    }

    @Test
    void testRemove() throws Exception {
        //given
        Long bno = 1L;

        //when
        boardService.removeWithReplies(bno);

        //then
        //여기서 봐야할 것은, reply 테이블이 먼저 삭제 되고, board 테이블을 조회한 후에 삭제하는지를 보면 된다.

    }

}