package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.response.GetMemberDetailResponse;
import kyungmin.katsee.domain.member.enums.*;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DetailMemberService {
  private final MemberRepository memberRepository;

  public GetMemberDetailResponse getMemberDetail() {
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    return GetMemberDetailResponse.builder()
      .memberId(member.getMemberId())
      .profileUrl(member.getProfileUrl())
      .nickName(member.getNickName())
      .age(member.getAge())
      .gender(member.getGender())
      .introduction(member.getIntroduction())
      .interests(
        member.getInterest().stream()
          .flatMap(i ->
            Stream.of(Interest.valueOf(i.getInterest().name()))
          )
          .toList()
      )
      .personalityTypes(
        member.getPersonalityType().stream()
          .flatMap(i ->
            Stream.of(PersonalityType.valueOf(i.getPersonality().name()))
          )
          .toList()
      )
      .talkStyles(
        member.getTalkStyle().stream()
          .flatMap(i ->
            Stream.of(TalkStyle.valueOf(i.getTalkStyle().name()))
          )
          .toList()
      )
      .friendStyles(
        member.getFriendStyle().stream()
          .flatMap(i ->
            Stream.of(FriendStyle.valueOf(i.getFriendStyle().name()))
          )
          .toList()
      )
      .onlineTalkStyles(
        member.getOnlineTalkStyle().stream()
          .flatMap(i ->
            Stream.of(OnlineTalkStyle.valueOf(i.getOnlineTalkStyle().name()))
          )
          .toList()
      )
      .relationshipDepth(
        member.getRelationshipDepth().stream()
          .flatMap(i ->
            Stream.of(RelationshipDepth.valueOf(i.getDepth().name()))
          )
          .toList()
      )
      .offlineTalkStyles(
        member.getIsOfflineMeeting().stream()
          .flatMap(i ->
            Stream.of(OfflineMeeting.valueOf(i.getIsOffline().name()))
          )
          .toList()
      )
      .interestPreference(
        member.getInterestPreference().stream()
          .flatMap(i ->
            Stream.of(InterestPreference.valueOf(i.getPreference().name()))
          )
          .toList()
      )
      .interestLevel(
        member.getInterestLevel().stream()
          .flatMap(i ->
            Stream.of(InterestLevel.valueOf(i.getLevel().name()))
          )
          .toList()
      ).build();
  }
}
