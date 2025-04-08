package kyungmin.katsee.domain.matching.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.matching.controller.request.UpdateMatchingStatusRequest;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatisticsResponse;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatusResponse;
import kyungmin.katsee.domain.matching.service.MatchingService;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/matching")
@RequiredArgsConstructor
@Tag(name = "매칭 현황 관련 API")
public class MatchingController {
  private final MatchingService matchingService;

  @PostMapping(value = "/request")
  @Operation(description = "매칭 요청")
  public ApiResponse<?> matchingRequest(@RequestParam String friendId) {
    matchingService.matching(friendId);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/statistics")
  @Operation(description = "매칭 통계")
  public ApiResponse<GetMatchingStatisticsResponse> matchingStatistics() {
    return ApiResponse.onSuccess(matchingService.getMatchingStatistics());
  }

  @GetMapping(value = "/statistics/status")
  @Operation(description = "매칭 상태 통계")
  public ApiResponse<GetMatchingStatusResponse> matchingStatus() {
    return ApiResponse.onSuccess(matchingService.getMatchingStatus());
  }

  @PatchMapping(value = "/status/update")
  @Operation(description = "매칭 상태 변경")
  public ApiResponse<?> matchingStatus(@RequestBody UpdateMatchingStatusRequest request) {
    matchingService.updateMatchingStatus(request);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/friends")
  @Operation(description = "친구 목록 조회")
  public ApiResponse<List<GetMemberResponse>> getFriends() {
    return ApiResponse.onSuccess(matchingService.getFriends());
  }

  @GetMapping(value = "/status/duplicate")
  @Operation(description = "이미 친구 상태인지 확인")
  public ApiResponse<Boolean> matchStatusDuplicate(@RequestParam String friendId) {
    return ApiResponse.onSuccess(matchingService.matchingStatusDuplicate(friendId));
  }

  @GetMapping(value = "/request/friends")
  @Operation(description = "요청 대기 중인 목록 조회")
  public ApiResponse<List<GetMemberResponse>> getRequestFriends() {
    return ApiResponse.onSuccess(matchingService.getRequestFriends());
  }
}
