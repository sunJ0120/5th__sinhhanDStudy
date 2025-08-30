package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    //Board 삭제시에 연관된 댓글도 삭제하기 위한 메서드
    //이거 그냥 cascade로 처리하면 되는거 아닌가...? 실무에서는 주로 어떤 식으로 하집.
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno ")
    void deleteByBno(Long bno); // 댓글 삭제

    //게시물로 댓글 목록 가져오기
    List<Reply> getRepiesByBoardOrderByRno(Board board);
}
