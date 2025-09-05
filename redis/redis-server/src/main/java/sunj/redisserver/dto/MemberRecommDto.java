package sunj.redisserver.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/*
원래 최종 들어가는 데이터
{
    userId : "123"
    name : "피용희"
    hobby : "음악감상"
    recentBuyCategory : "카페"
    limit : 100000
    balance : 40000
    require : "추천 시스템"
    mood : "좋음"
}

여기서, 일단은 캐싱 TEST니까 recentBuyCategory : "카페"는 빼고 생각한다.
 */
@ToString
@Getter
@Builder
public class MemberRecommDto {
    private String userId;
    private String name;
    private String hobby;
//    private String recentBuyCategory;
    private int creditLimit;
    private int balance;
    private String require;
    private String mood;

    @QueryProjection
    public MemberRecommDto(
                String userId,
                String name,
                String hobby,
//                String recentBuyCategory,
                int creditLimit,
                int balance,
                String require,
                String mood
            ){
        this.userId = userId;
        this.name = name;
        this.hobby = hobby;
//        this.recentBuyCategory = recentBuyCategory;
        this.creditLimit = creditLimit;
        this.balance = balance;
        this.require = require;
        this.mood = mood;
    }
}
