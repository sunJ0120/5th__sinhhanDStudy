package sunj.studyjpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Entity
@Table(name = "shorts")
@Builder
@Getter
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class Shorts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false, length = 100)
    private String shortsName;

    @Column(nullable = false, length = 100)
    private String videoName;

    @Column(nullable = false, length = 150)
    private String shortsExplain;

    @Column(nullable = false)
    private String thumbnail;

    //------ 연관관계 편의 메서드 ---------
    public void addCustomer(Customer customer) {
        if (this.customer != null) {
            throw new IllegalStateException("이미 고객이 지정된 Shorts입니다.");
        }

        // 1) 주인(FK) 쪽 먼저 세팅
        this.customer = Objects.requireNonNull(customer, "customer must not be null");

        //반대편에 연관관계 맺어준다.
        if (!customer.getShortsList().contains(this)) {
            customer.getShortsList().add(this);
        }
    }
}
