package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.matching.repository.MatchingRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.request.MemberCreateRequest;
import kyungmin.katsee.domain.member.controller.request.MemberDetailRequest;
import kyungmin.katsee.domain.member.controller.request.UpdateDetailRequest;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberDetailResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.enums.Interest;
import kyungmin.katsee.domain.member.repository.*;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final MatchingRepository matchingRepository;

  private final CreateMemberService createService;
  private final DetailMemberService detailMemberService;

  public GetMemberResponse getMember() {
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    return GetMemberResponse.builder()
      .memberId(member.getMemberId())
      .profileUrl(member.getProfileUrl())
      .nickName(member.getNickName())
      .age(member.getAge())
      .gender(member.getGender().name())
      .introduction(member.getIntroduction())
      .interests(
        member.getInterest().stream()
          .flatMap(i ->
            Stream.of(Interest.valueOf(i.getInterest().name()))
          ).toList()
      )
      .build();
  }

  public GetMemberResponse getMemberById(String memberId) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    return GetMemberResponse.builder()
      .memberId(member.getMemberId())
      .profileUrl(member.getProfileUrl())
      .nickName(member.getNickName())
      .age(member.getAge())
      .gender(member.getGender().name())
      .introduction(member.getIntroduction())
      .interests(
        member.getInterest().stream()
          .flatMap(i ->
            Stream.of(Interest.valueOf(i.getInterest().name()))
          ).toList()
      )
      .build();
  }

  public GetDuplicateIdResponse duplicateId(String id) {
    return (memberRepository.existsById(id)) ?
      GetDuplicateIdResponse.builder()
        .isDuplicate(true)
        .message("아이디가 중복됩니다.")
        .build() :
      GetDuplicateIdResponse.builder()
        .isDuplicate(false)
        .message("사용 가능한 아이디입니다.")
        .build();
  }

  public void createMember(MemberCreateRequest request) {
    createService.createMember(request);
  }

  public void createMemberDetail(MemberDetailRequest request) {
    createService.createMemberDetail(request);
  }

  public GetMemberDetailResponse getMemberDetail(String memberId) {
    return detailMemberService.getMemberDetail(memberId);
  }

  public void updateMemberDetail(UpdateDetailRequest request) {
    detailMemberService.updateMemberDetail(request);
  }

  public List<GetMemberResponse> allMembers() {
    List<GetMemberResponse> responseMembers = new ArrayList<>();

    memberRepository.findAll().forEach(member -> {
      if (member.getMemberId().equals("admin")) {} // 관리자는 무시
      else if (!member.getMemberId().equals(SecurityUtil.authMemberId())) {
        responseMembers.add(
          GetMemberResponse.builder()
            .memberId(member.getMemberId())
            .profileUrl(member.getProfileUrl())
            .nickName(member.getNickName())
            .age(member.getAge())
            .gender(member.getGender().value)
            .introduction(member.getIntroduction())
            .interests(
              member.getInterest().stream()
                .flatMap(i ->
                  Stream.of(Interest.valueOf(i.getInterest().name()))
                ).toList()
            ).build()
        );
      }
    });

    for (int i = 0; i < responseMembers.size(); i++) {
      int finalI = i;
      matchingRepository.getFriends(SecurityUtil.authMemberId()).forEach(friend -> {
        if (
          !friend.getFriend().getMemberId().equals(responseMembers.get(finalI).getMemberId()) ||
          !responseMembers.get(finalI).getMemberId().equals(SecurityUtil.authMemberId())
        ) { responseMembers.remove(finalI); }
      });
    }

    return responseMembers;
  }
}
