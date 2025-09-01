package sunj.studyjpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunj.studyjpa.domain.entity.Customer;
import sunj.studyjpa.domain.entity.Shorts;

import java.time.LocalDate;
import java.util.List;

/*
DATA JPA 예시 레포지토리

- DATA JPA의 경우, 기본적인 CRUD를 전부 제공해준다.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
