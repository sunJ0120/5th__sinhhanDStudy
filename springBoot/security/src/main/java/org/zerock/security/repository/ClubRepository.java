package org.zerock.security.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.security.entity.ClubMember;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<ClubMember, String> {

    //연관관계(roleSet)를 미리 로딩해서 N+1 문제를 피하거나, LazyLoading 시 발생할 수 있는 문제를 예방하기 위해서이다.
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.fromSocial = :social and m.email = :email")
    Optional<ClubMember> findByEmail(@Param("email") String email, @Param("social") boolean social);
}
