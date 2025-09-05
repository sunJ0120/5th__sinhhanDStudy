package sunj.redisserver.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
json으로 파싱할 캐싱 전용 dto
 */
@Getter
@ToString
@Builder
@NoArgsConstructor //REDIS 역직렬화를 위해서 반드시 필요하다.
public class MemberCachingDto {
    private String name;
    private int creditLimit;

    @QueryProjection
    public MemberCachingDto(String name, int creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
    }
}
