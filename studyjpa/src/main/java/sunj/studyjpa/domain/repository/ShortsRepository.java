package sunj.studyjpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunj.studyjpa.domain.entity.Shorts;

import java.util.List;

public interface ShortsRepository extends JpaRepository<Shorts, Long>, ShortsQueryRepository {
    //해당 유저가 작성한 모든 shorts를 불러오고자 할 때
    List<Shorts> findByCustomerId(Long customerId);
}
