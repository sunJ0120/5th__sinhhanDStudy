package sunj.studyjpa.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
@QueryProjection 사용을 위한 중간 내부 DTO
 */

@Getter
@NoArgsConstructor
@Builder
@ToString
public class FilteredShortsResponse {
    private Long id;
    private String shortsName;
    private String thumbnail;

    //이를 통해 QDTO를 생성해서, DTO 변환 과정없이 (QueryDSL이 알아서 해줌) 편하게 사용이 가능하다.
    @QueryProjection
    public FilteredShortsResponse(Long id, String shortsName, String thumbnail) {
        this.id = id;
        this.shortsName = shortsName;
        this.thumbnail = thumbnail;
    }
}
