package kyungmin.katsee.domain.matching.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatisticsResponse;
import kyungmin.katsee.domain.matching.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/matching")
@RequiredArgsConstructor
@Tag(name = "매칭 현황 관련 API")
public class MatchingController {
  private final MatchingService matchingService;

  // 매칭
  @PostMapping(value = "/request")
  @Operation(description = "매칭 요청")
  public ApiResponse<?> matchingRequest(@RequestParam String friendId) {
    matchingService.matching(friendId);
    return ApiResponse.onSuccess();
  }

  // 전체 매칭 통계
  @GetMapping(value = "/statistics")
  @Operation(description = "전체 매칭 통계")
  public ApiResponse<GetMatchingStatisticsResponse> matchingStatus() {
    return ApiResponse.onSuccess(matchingService.getMatchingStatistics());
  }

  // 신규 매칭 통계
  // 각 매칭 통계
}
