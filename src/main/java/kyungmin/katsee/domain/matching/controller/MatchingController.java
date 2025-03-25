package kyungmin.katsee.domain.matching.controller;

import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.matching.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/matching")
@RequiredArgsConstructor
public class MatchingController {
  private final MatchingService matchingService;

  // 매칭
  @PostMapping(value = "/request")
  public ApiResponse<?> matchingRequest(@RequestParam String friendId) {
    matchingService.matching(friendId);
    return ApiResponse.onSuccess();
  }

  // 전체 매칭 통계
  // 신규 매칭 통계
  // 각 매칭 통계
}
