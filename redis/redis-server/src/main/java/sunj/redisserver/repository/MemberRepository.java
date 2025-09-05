package sunj.redisserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunj.redisserver.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository{
    int findBalanceByMemberId(Long memberId);
    String findHobbyByMemberId(Long memberId);
}
