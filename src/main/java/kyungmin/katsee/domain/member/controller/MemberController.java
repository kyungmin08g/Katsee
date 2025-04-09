package kyungmin.katsee.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.member.controller.request.MemberDetailRequest;
import kyungmin.katsee.domain.member.controller.request.MemberCreateRequest;
import kyungmin.katsee.domain.member.controller.request.UpdateDetailRequest;
import kyungmin.katsee.domain.member.controller.response.*;
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

  /**
   * 회원 로그인
   * @param memberId : 회원 아이디
   * @param password : 비밀번호
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/login")
  @Operation(description = "로그인")
  public ApiResponse<?> login(@RequestParam String memberId, @RequestParam String password) {
    return ApiResponse.onSuccess();
  }

  /**
   * 회원가입
   * @param request : 회원 등록 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/member/create")
  @Operation(description = "회원 등록")
  public ApiResponse<?> createMember(@RequestBody MemberCreateRequest request) {
    memberService.createMember(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 회원 상세 정보 등록
   * @param request : 회원 상세 정보 등록 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/member/create/detail")
  @Operation(description = "회원 상세 정보 등록")
  public ApiResponse<?> createMemberDetails(@RequestBody MemberDetailRequest request) {
    memberService.createMemberDetail(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 회원 조회
   * @return : 성공시 회원 조회 응답 객체 반환
   */
  @GetMapping(value = "/member")
  @Operation(description = "회원 조회")
  public ApiResponse<GetMemberResponse> getMember() {
    return ApiResponse.onSuccess(memberService.getMember());
  }

  /**
   * 회원 아이디를 통한 특정 회원 조회
   * @param memberId : 회원 아이디
   * @return : 성공시 회원 조회 응답 객체 반환
   */
  @GetMapping(value = "/member/{memberId}")
  @Operation(description = "ID를 통한 회원 조회")
  public ApiResponse<GetMemberResponse> getMemberById(@PathVariable("memberId") String memberId) {
    return ApiResponse.onSuccess(memberService.getMemberById(memberId));
  }

  /**
   * 회원 상세 조회
   * @param memberId : 회원 아이디
   * @return : 성공시 회원 상세 정보 반환
   */
  @GetMapping(value = "/member/detail")
  @Operation(description = "회원 상세 조회")
  public ApiResponse<GetMemberDetailResponse> getMemberDetail(@RequestParam String memberId) {
    return ApiResponse.onSuccess(memberService.getMemberDetail(memberId));
  }

  /**
   * 모든 회원 목록 조회
   * @return : 성공시 모든 회원 목록 반환
   */
  @GetMapping(value = "/member/all")
  @Operation(description = "모든 회원 조회")
  public ApiResponse<List<GetMemberResponse>> allMembers() {
    return ApiResponse.onSuccess(memberService.allMembers());
  }

  /**
   * 회원 정보 수정
   * @param request : 회원 정보 수정 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PatchMapping(value = "/member/update")
  @Operation(description = "회원 정보 수정")
  public ApiResponse<?> updateMemberDetail(@RequestBody UpdateDetailRequest request) {
    memberService.updateMemberDetail(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 아이디 중복 확인
   * @param memberId : 회원 아이디
   * @return : 성공시 아이디 중복 여부 반환
   */
  @GetMapping(value = "/member/duplicate/{id}")
  @Operation(description = "회원 ID 중복 여부 확인")
  public ApiResponse<GetDuplicateIdResponse> getDuplicateId(@PathVariable("id") String memberId) {
    return ApiResponse.onSuccess(memberService.duplicateId(memberId));
  }

  /**
   * 전체 사용자 & 주요 연령대 조회 - 관리자만 접근 가능
   * @return : 성공시 전체 사용자 & 주요 연령대 조회 응답 객체 반환
   */
  @GetMapping(value = "/admin/statistics")
  @Operation(description = "전체 사용자 & 주요 연령대 조회")
  public ApiResponse<GetAdminStatisticsResponse> members() {
    return ApiResponse.onSuccess(memberService.getAdminStatistics());
  }

  /**
   * 친구 추천
   * @return : 성공시 친구 추천 응답 객체 반환
   */
  @GetMapping(value = "/recommend/friend")
  @Operation(description = "친구 추천")
  public ApiResponse<List<GetRecommendFriendResponse>> recommendFriends() {
    return ApiResponse.onSuccess(recommendService.recommendFriends());
  }
}
