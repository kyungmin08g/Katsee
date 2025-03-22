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

  @GetMapping(value = "/matching-status")
  @Operation(description = "매칭 상태 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> matchingStatusEnum() {
    return ApiResponse.onSuccess(enumService.getMatchingStatusEnums());
  }

  @GetMapping(value = "/friend-style")
  @Operation(description = "친구 스타일 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> friendStyleEnum() {
    return ApiResponse.onSuccess(enumService.getFriendStyleEnums());
  }

  @GetMapping(value = "/interest")
  @Operation(description = "관심사 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestEnum() {
    return ApiResponse.onSuccess(enumService.getInterestEnums());
  }

  @GetMapping(value = "/interest-level")
  @Operation(description = "관심사 레벨 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestLevelEnum() {
    return ApiResponse.onSuccess(enumService.getInterestLevelEnums());
  }

  @GetMapping(value = "/interest-preference")
  @Operation(description = "관심사 선호도 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> interestPreferenceEnum() {
    return ApiResponse.onSuccess(enumService.getInterestPreferenceEnums());
  }

  @GetMapping(value = "/offline-meeting")
  @Operation(description = "오프라인 만난 허용 여부 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> offlineMeetingEnum() {
    return ApiResponse.onSuccess(enumService.getOfflineMeetingEnums());
  }

  @GetMapping(value = "/online-talk-style")
  @Operation(description = "온라인(음성/영상) 대화 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> onlineTalkStyleEnum() {
    return ApiResponse.onSuccess(enumService.getOnlineTalkStyleEnums());
  }

  @GetMapping(value = "/personality-type")
  @Operation(description = "성격 유형 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> personalityTypeEnum() {
    return ApiResponse.onSuccess(enumService.getPersonalityTypeEnums());
  }

  @GetMapping(value = "/relationship-depth")
  @Operation(description = "관계 깊이 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> relationshipDepthEnum() {
    return ApiResponse.onSuccess(enumService.getRelationshipDepthEnums());
  }

  @GetMapping(value = "/talk-style")
  @Operation(description = "대화 스타일 Enum 조회")
  public ApiResponse<List<GetEnumResponse>> talkStyleEnum() {
    return ApiResponse.onSuccess(enumService.getTalkStyleEnums());
  }
}
