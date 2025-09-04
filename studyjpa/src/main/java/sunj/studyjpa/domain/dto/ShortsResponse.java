package sunj.studyjpa.domain.dto;

/*
API 명세 안에 들어가는 모든 REQUEST, RESPONSE 객체는 전부 엔티티 그 자체가 아닌, dto를 사용해야 한다!!!!!
 */

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShortsResponse {
    private Long id;
    private String shortsName;
    private String thumbnail;
}
