package sunj.studyjpa.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/*
fetchJoin 실험용 dto
 */
@Getter
@NoArgsConstructor
@ToString
public class PaymentWithCustomerDto {
    private Long paymentId;
    private int price;
    private int point;
    private String requestName;
    private LocalDateTime date;

    // Customer 정보도 일부 포함
    private Long customerId;
    private String customerName;
    private String nickname;

    @Builder
    @QueryProjection
    public PaymentWithCustomerDto(
            Long paymentId,
            int price,
            int point,
            String requestName,
            LocalDateTime date,
            Long customerId,
            String customerName,
            String nickname
    ){
        this.paymentId = paymentId;
        this.price = price;
        this.point = point;
        this.requestName = requestName;
        this.date = date;
        this.point = point;
        this.customerId = customerId;
        this.customerName = customerName;
        this.nickname = nickname;
    }
}
