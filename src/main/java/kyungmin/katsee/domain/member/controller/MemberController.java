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
import kyungmin.katsee.domain.member.controller.response.GetRecommendFriendResponse;
import kyungmin.katsee.domain.member.service.MemberService;
import kyungmin.katsee.domain.member.service.RecommendFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 관련 API")
public class MemberController {
  private final MemberService memberService;
  private final RecommendFriendService recommendService;

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

  @GetMapping(value = "/member/{memberId}")
  @Operation(description = "ID를 통한 회원 조회")
  public ApiResponse<GetMemberResponse> getMemberById(@PathVariable("memberId") String memberId) {
    return ApiResponse.onSuccess(memberService.getMemberById(memberId));
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

  @GetMapping(value = "/recommend/friend")
  @Operation(description = "친구 추천")
  public ApiResponse<List<GetRecommendFriendResponse>> recommendFriends() {
    return ApiResponse.onSuccess(recommendService.recommendFriends());
  }

  @GetMapping(value = "/member/all")
  @Operation(description = "모든 회원 조회")
  public ApiResponse<List<GetMemberResponse>> allMembers() {
    return ApiResponse.onSuccess(memberService.allMembers());
  }
}
