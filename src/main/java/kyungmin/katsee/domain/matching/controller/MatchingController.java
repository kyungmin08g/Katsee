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

  /**
   * 매칭 요청 보내기
   * @param friendId : 친구 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/request")
  @Operation(description = "매칭 요청")
  public ApiResponse<?> matchingRequest(@RequestParam String friendId) {
    matchingService.matching(friendId);
    return ApiResponse.onSuccess();
  }

  /**
   * 매칭 현황(통계) 조회
   * @return : 성공시 매칭 현황 조회 응답 객체 반환
   */
  @GetMapping(value = "/statistics")
  @Operation(description = "매칭 통계")
  public ApiResponse<GetMatchingStatisticsResponse> matchingStatistics() {
    return ApiResponse.onSuccess(matchingService.getMatchingStatistics());
  }

  /**
   * 매칭 상태 통계
   * @return : 성공시 매칭 상태 통계 조회 응답 객체 반환
   */
  @GetMapping(value = "/statistics/status")
  @Operation(description = "매칭 상태 통계")
  public ApiResponse<GetMatchingStatusResponse> matchingStatus() {
    return ApiResponse.onSuccess(matchingService.getMatchingStatus());
  }

  /**
   * 매칭 상태 수정
   * @param request : 매칭 상태 변경 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PatchMapping(value = "/status/update")
  @Operation(description = "매칭 상태 변경")
  public ApiResponse<?> matchingStatus(@RequestBody UpdateMatchingStatusRequest request) {
    matchingService.updateMatchingStatus(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 친구 목록 조회
   * @return : 성공시 친구 목록 조회 응답 객체 반환
   */
  @GetMapping(value = "/friends")
  @Operation(description = "친구 목록 조회")
  public ApiResponse<List<GetMemberResponse>> getFriends() {
    return ApiResponse.onSuccess(matchingService.getFriends());
  }

  /**
   * 친구 상태인지 확인
   * @param friendId : 친구 아이디
   * @return : 성공시 Boolean 타입으로 성공 여부 반환
   */
  @GetMapping(value = "/status/duplicate")
  @Operation(description = "친구 상태인지 확인")
  public ApiResponse<Boolean> matchStatusDuplicate(@RequestParam String friendId) {
    return ApiResponse.onSuccess(matchingService.matchingStatusDuplicate(friendId));
  }

  /**
   * 요청 대기 중인 목록 조회
   * @return : 성공시 요청 대기 중인 회원 목록 객체 반환
   */
  @GetMapping(value = "/request/friends")
  @Operation(description = "요청 대기 중인 목록 조회")
  public ApiResponse<List<GetMemberResponse>> getRequestFriends() {
    return ApiResponse.onSuccess(matchingService.getRequestFriends());
  }
}
