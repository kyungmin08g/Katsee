package kyungmin.katsee.domain.member.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.member.controller.request.MemberRegisterRequest;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
@Tag(name = "회원 관련 API")
public class MemberController {
  private final MemberService memberService;

  // 회원 등록 API
  @PostMapping(value = "/register")
  public ApiResponse<?> memberRegister(@RequestBody MemberRegisterRequest request) {
    memberService.registerMember(request);
    return ApiResponse.onSuccess();
  }

  // 회원 상세 정보 등록 API

  // 회원 조회 API
  @GetMapping(value = "/{id}")
  public ApiResponse<GetMemberResponse> getMember(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.getMember(memberId));
  }

  // 회원 ID 중복 여부
  @GetMapping(value = "/duplicate/{id}")
  public ApiResponse<GetDuplicateIdResponse> getDuplicateId(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.duplicateId(memberId));
  }

  // 회원 상세 API

  // 회원 수정 API

  // 회원 삭제 API
  @GetMapping(value = "/delete/{id}")
  public ApiResponse<?> deleteMember(@PathVariable("id") String memberId) {
    memberService.deleteMember(memberId);
    return ApiResponse.onSuccess();
  }

}
