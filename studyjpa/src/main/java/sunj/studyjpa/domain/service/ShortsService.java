package sunj.studyjpa.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sunj.studyjpa.domain.dto.FilteredShortsResponse;
import sunj.studyjpa.domain.dto.ShortsResponse;
import sunj.studyjpa.domain.repository.ShortsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortsService {

    private final ShortsRepository shortsRepository;

    public List<ShortsResponse> shortsTestService(String nickname, String keyword){
        List<FilteredShortsResponse> filteredShortsResponses = shortsRepository.searchShorts(nickname, keyword);

        //이렇게 외부 노출용 DTO로 변환한다.
        // 외부 노출용 DTO로 변환
        return filteredShortsResponses.stream()
                .map(filtered -> ShortsResponse.builder()
                        .id(filtered.getId())
                        .shortsName(filtered.getShortsName())
                        .thumbnail(filtered.getThumbnail())
                        .build())
                .toList();
    }
}
