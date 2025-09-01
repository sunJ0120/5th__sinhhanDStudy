package sunj.studyjpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Builder
@Getter
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //다대일 단방향 매핑 - 연관관계의 주인이 "다"쪽이므로 여기에 매핑을 진행한다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    // Payment 가 FK를 가짐 → Payment 가 주인
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "payment_request_id", unique = true, nullable = false)
//    private PaymentRequest paymentRequest;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    int point;

    @Column(nullable = false)
    String requestName;

    @Column(nullable = false)
    private LocalDateTime date; //paidAt?
}
