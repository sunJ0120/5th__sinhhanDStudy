package sunj.be.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity들이 이 클래스를 상속하면, createdAt/updatedAt 컬럼이 매핑된다.
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 포함, JPA가 save/flush 시점에 저 애노테이션 붙은 필드를 감시해서 넣어준다.
public abstract class BaseTime {

    @CreatedDate //엔티티 처음 persist 될 때 자동으로 createdAt 세팅
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티가 update 될 때마다 자동으로 updatedAt 갱신
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
