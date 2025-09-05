package sunj.redisserver.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

/*
json으로 파싱할 캐싱 전용 dto
 */
@Getter
@ToString
public class MemberCachingDto {
    private String name;
    private int creditLimit;

    @QueryProjection
    public MemberCachingDto(String name, int creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
    }
}
