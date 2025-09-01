package sunj.studyjpa.domain.controller;

/*
api test용 컨트롤러..

당연히 service > controller가 정석이지만, 간단한 예제로 보여주기 위해 그냥 repository 그대로 사용했다.
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sunj.studyjpa.domain.dto.ShortsResponseDto;
import sunj.studyjpa.domain.repository.ShortsRepository;

import java.util.List;

@Tag(name = "Shorts", description = "쇼츠 조회 TEST API")
@RestController
@RequiredArgsConstructor
public class ShortsController {
    private final ShortsRepository shortsRepository;

    //검색 결과를 보여주는 Get API를 작성해보자.
    //당연히 보내는 파라미터들도 가능하면 dto로 작성하는게 좋음 (validation 달려면,) 그냥 간단 예제라 이렇게 구현한거임
    @Operation(summary = "닉네임+키워드로 쇼츠 검색")
    @GetMapping("/v1/members")
    public List<ShortsResponseDto> searchShortsV1(
            @Parameter(description = "사용자 닉네임", example = "피용히")
            @RequestParam String nickname,
            @Parameter(description = "검색 키워드", example = "다이소")
            @RequestParam String keyword) {
        return shortsRepository.searchShorts(nickname, keyword);
    }
}
