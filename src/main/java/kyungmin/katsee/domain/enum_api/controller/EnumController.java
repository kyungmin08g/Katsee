package kyungmin.katsee.domain.enum_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.enum_api.controller.response.GetEnumResponse;
import kyungmin.katsee.domain.enum_api.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/enum")
@RequiredArgsConstructor
@Tag(name = "Enum 관련 API")
public class EnumController {
  private final EnumService enumService;

  /**
   * 매칭 상태 Enum 조회
   * @return : 성공시 매칭 상태 Enum 응답 객체 반환
   */
  @GetMapping(value = "/matching-status")
  @Operation(description = "매칭 상태 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> matchingStatusEnum() {
    return ApiResponse.onSuccess(enumService.getMatchingStatusEnums());
  }

  /**
   * 친구 스타일 Enum 조회
   * @return : 성공시 친구 스타일 Enum 응답 객체 반환
   */
  @GetMapping(value = "/friend-style")
  @Operation(description = "친구 스타일 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> friendStyleEnum() {
    return ApiResponse.onSuccess(enumService.getFriendStyleEnums());
  }

  /**
   * 관심사 Enum 조회
   * @return : 성공시 매칭 상태 목록에 관한 Enum 응답 객체 반환
   */
  @GetMapping(value = "/interest")
  @Operation(description = "관심사 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestEnum() {
    return ApiResponse.onSuccess(enumService.getInterestEnums());
  }

  /**
   * 관심사 레벨 Enum 조회
   * @return : 성공시 관심사 레벨 Enum 응답 객체 반환
   */
  @GetMapping(value = "/interest-level")
  @Operation(description = "관심사 레벨 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestLevelEnum() {
    return ApiResponse.onSuccess(enumService.getInterestLevelEnums());
  }

  /**
   * 관심사 선호도 Enum 조회
   * @return : 성공시 관심사 선호도 Enum 응답 객체 반환
   */
  @GetMapping(value = "/interest-preference")
  @Operation(description = "관심사 선호도 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestPreferenceEnum() {
    return ApiResponse.onSuccess(enumService.getInterestPreferenceEnums());
  }

  /**
   * 오프라인 만난 허용 여부 Enum 조회
   * @return : 성공시 오프라인 만난 허용 여부 Enum 응답 객체 반환
   */
  @GetMapping(value = "/offline-meeting")
  @Operation(description = "오프라인 만난 허용 여부 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> offlineMeetingEnum() {
    return ApiResponse.onSuccess(enumService.getOfflineMeetingEnums());
  }

  /**
   * 온라인 대화 스타일 Enum 조회
   * @return : 성공시 온라인 대화 스타일(음성/영상/채팅) Enum 응답 객체 반환
   */
  @GetMapping(value = "/online-talk-style")
  @Operation(description = "온라인 대화 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> onlineTalkStyleEnum() {
    return ApiResponse.onSuccess(enumService.getOnlineTalkStyleEnums());
  }

  /**
   * 성격 유형 Enum 조회
   * @return : 성공시 성격 유형 Enum 응답 객체 반환
   */
  @GetMapping(value = "/personality-type")
  @Operation(description = "성격 유형 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> personalityTypeEnum() {
    return ApiResponse.onSuccess(enumService.getPersonalityTypeEnums());
  }

  /**
   * 관계 깊이 Enum 조회
   * @return : 성공시 관계 깊이 Enum 응답 객체 반환
   */
  @GetMapping(value = "/relationship-depth")
  @Operation(description = "관계 깊이 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> relationshipDepthEnum() {
    return ApiResponse.onSuccess(enumService.getRelationshipDepthEnums());
  }

  /**
   * 대화 스타일 Enum 조회
   * @return : 성공시 대화 스타일 Enum 응답 객체 반환
   */
  @GetMapping(value = "/talk-style")
  @Operation(description = "대화 스타일 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> talkStyleEnum() {
    return ApiResponse.onSuccess(enumService.getTalkStyleEnums());
  }
}
