package kyungmin.katsee.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.member.controller.request.MemberRegisterRequest;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
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
  @Operation(description = "회원 로그인")
  public ApiResponse<?> login(@RequestParam String memberId, @RequestParam String password) {
    return ApiResponse.onSuccess();
  }

  // 회원 등록 API
  @PostMapping(value = "/member/register")
  @Operation(description = "회원 등록")
  public ApiResponse<?> memberRegister(@RequestBody MemberRegisterRequest request) {
    memberService.registerMember(request);
    return ApiResponse.onSuccess();
  }

  // 회원 상세 정보 등록 API

  // 회원 조회 API
  @GetMapping(value = "/member/get/{id}")
  @Operation(description = "회원 조회")
  public ApiResponse<GetMemberResponse> getMember(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.getMember(memberId));
  }

  // 회원 ID 중복 여부
  @GetMapping(value = "/member/duplicate/{id}")
  @Operation(description = "회원 ID 중복 여부 확인")
  public ApiResponse<GetDuplicateIdResponse> getDuplicateId(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.duplicateId(memberId));
  }

  // 회원 상세 API

  // 회원 수정 API

  // 회원 삭제 API
  @DeleteMapping(value = "/member/delete/{id}")
  @Operation(description = "회원 삭제")
  public ApiResponse<?> deleteMember(@PathVariable("id") String memberId) {
    memberService.deleteMember(memberId);
    return ApiResponse.onSuccess();
  }

}
