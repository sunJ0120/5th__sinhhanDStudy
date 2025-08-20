package org.sinhan.ch3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data //사실 JPA 할때는 안 쓰는게 좋다. 무한참조가 일어난다. 이거 분명 알고 있었는데 기억이 안나네...
@Builder(toBuilder = true)
public class SampleDTO {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
