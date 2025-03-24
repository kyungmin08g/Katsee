package kyungmin.katsee.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.member.controller.request.MemberDetailRequest;
import kyungmin.katsee.domain.member.controller.request.MemberCreateRequest;
import kyungmin.katsee.domain.member.controller.request.UpdateDetailRequest;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberDetailResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 관련 API")
public class MemberController {
  private final MemberService memberService;

  @PostMapping(value = "/login")
  @Operation(description = "로그인")
  public ApiResponse<?> login(@RequestParam String memberId, @RequestParam String password) {
    return ApiResponse.onSuccess();
  }

  @PostMapping(value = "/member/create")
  @Operation(description = "회원 등록")
  public ApiResponse<?> createMember(@RequestBody MemberCreateRequest request) {
    memberService.createMember(request);
    return ApiResponse.onSuccess();
  }

  @PostMapping(value = "/member/create/detail")
  @Operation(description = "회원 상세 정보 등록")
  public ApiResponse<?> createMemberDetails(@RequestBody MemberDetailRequest request) {
    memberService.createMemberDetail(request);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/member")
  @Operation(description = "회원 조회")
  public ApiResponse<GetMemberResponse> getMember() {
    return ApiResponse.onSuccess(memberService.getMember());
  }

  @GetMapping(value = "/member/detail")
  @Operation(description = "회원 상세 조회")
  public ApiResponse<GetMemberDetailResponse> getMemberDetail() {
    return ApiResponse.onSuccess(memberService.getMemberDetail());
  }

  @PatchMapping(value = "/member/update")
  @Operation(description = "회원 정보 수정")
  public ApiResponse<?> updateMemberDetail(@RequestBody UpdateDetailRequest request) {
    memberService.updateMemberDetail(request);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/member/duplicate/{id}")
  @Operation(description = "회원 ID 중복 여부 확인")
  public ApiResponse<GetDuplicateIdResponse> getDuplicateId(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.duplicateId(memberId));
  }
}
