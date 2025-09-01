package sunj.studyjpa.domain.dto;

/*
API 명세 안에 들어가는 모든 REQUEST, RESPONSE 객체는 전부 엔티티 그 자체가 아닌, dto를 사용해야 한다!!!!!
 */

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Builder
@ToString
public class ShortsResponseDto {
    private Long id;
    private String shortsName;
    private String thumbnail;

    //이를 통해 QDTO를 생성해서, DTO 변환 과정없이 (QueryDSL이 알아서 해줌) 편하게 사용이 가능하다.
    @QueryProjection
    public ShortsResponseDto(Long id, String shortsName, String thumbnail) {
        this.id = id;
        this.shortsName = shortsName;
        this.thumbnail = thumbnail;
    }
}
