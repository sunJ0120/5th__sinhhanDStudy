package org.sinhan.helloworld.repository;

import org.sinhan.helloworld.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Spring Data JPA를 사용하여 Memo 엔티티에 대한 CRUD 작업을 수행하는 리포지토리 인터페이스입니다.
이 인터페이스는 JpaRepository를 상속받아 기본적인 CRUD 메소드들을 자동으로 제공합니다.

MemoRepository 인터페이스는 Memo 엔티티의 ID 타입이 Long임을 명시한다.
 */

public interface MemoRepository extends JpaRepository<Memo, Long>, QuerydslPredicateExecutor<Memo> {
    //Memo 엔티티의 번호가 from과 to 사이에 있는 모든 메모를 조회하는 메소드
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // Pageable 객체 (요청 페이지, 페이지 당 개수, 정렬)
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    // 삭제 처리는 deleteByMno~ 메서드를 이용해서 삭제한다.
    void deleteMemoByMnoLessThan(Long num); // 번호가 num보다 작은 메모 삭제

    @Query(value = "SELECT m.mno, m.memoText, CURRENT_DATE FROM Memo m WHERE m.mno > :mno",
        countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);


    @Query("SELECT m FROM Memo m WHERE m.memoText LIKE %:memo% ORDER BY m.mno DESC")
    List<Memo> findByMemoText(@Param("memo") String memo);

    //쿼리 메서드
    List<Memo> findByMemoTextContaining(String memo);

    // JPQL
    @Query(value = "select * from tbl_memo where memo_text like concat('%', ?1 '%')", nativeQuery = true)
    List<Memo> selectTblMemo(String memo);
}
