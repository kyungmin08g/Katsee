package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.*;
import kyungmin.katsee.domain.member.controller.request.UpdateDetailRequest;
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

  public GetMemberDetailResponse getMemberDetail(String memberId) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    return GetMemberDetailResponse.builder()
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
      )
      .personalityTypes(
        member.getPersonalityType().stream()
          .flatMap(i ->
            Stream.of(PersonalityType.valueOf(i.getPersonality().name()))
          ).toList()
      )
      .talkStyles(
        member.getTalkStyle().stream()
          .flatMap(i ->
            Stream.of(TalkStyle.valueOf(i.getTalkStyle().name()))
          ).toList()
      )
      .friendStyles(
        member.getFriendStyle().stream()
          .flatMap(i ->
            Stream.of(FriendStyle.valueOf(i.getFriendStyle().name()))
          ).toList()
      )
      .onlineTalkStyles(
        member.getOnlineTalkStyle().stream()
          .flatMap(i ->
            Stream.of(OnlineTalkStyle.valueOf(i.getOnlineTalkStyle().name()))
          ).toList()
      )
      .relationshipDepth(
        member.getRelationshipDepth().stream()
          .flatMap(i ->
            Stream.of(RelationshipDepth.valueOf(i.getDepth().name()))
          ).toList()
      )
      .offlineTalkStyles(
        member.getIsOfflineMeeting().stream()
          .flatMap(i ->
            Stream.of(OfflineMeeting.valueOf(i.getIsOffline().name()))
          ).toList()
      )
      .interestPreference(
        member.getInterestPreference().stream()
          .flatMap(i ->
            Stream.of(InterestPreference.valueOf(i.getPreference().name()))
          ).toList()
      )
      .interestLevel(
        member.getInterestLevel().stream()
          .flatMap(i ->
            Stream.of(InterestLevel.valueOf(i.getLevel().name()))
          ).toList()
      ).build();
  }

  public void updateMemberDetail(UpdateDetailRequest request) {
    String memberId = SecurityUtil.authMemberId();
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    member.getInterest().clear();
    member.getPersonalityType().clear();
    member.getTalkStyle().clear();
    member.getFriendStyle().clear();
    member.getOnlineTalkStyle().clear();
    member.getRelationshipDepth().clear();
    member.getIsOfflineMeeting().clear();
    member.getInterestPreference().clear();
    member.getInterestLevel().clear();

    memberRepository.save(
      member.toBuilder()
        .memberId(memberId)
        .profileUrl(request.profileUrl())
        .nickName(request.nickName())
        .age(request.age())
        .gender(request.gender())
        .introduction(request.introduction())
        .interest(
          request.interests().stream()
            .flatMap(i ->
              Stream.of(
                MemberInterest.builder()
                  .interest(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .personalityType(
          request.personalityTypes().stream()
            .flatMap(i ->
              Stream.of(
                MemberPersonalityType.builder()
                  .personality(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .talkStyle(
          request.talkStyles().stream()
            .flatMap(i ->
              Stream.of(
                MemberTalkStyle.builder()
                  .talkStyle(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .friendStyle(
          request.friendStyles().stream()
            .flatMap(i ->
              Stream.of(
                MemberFriendStyle.builder()
                  .friendStyle(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .relationshipDepth(
          request.relationshipDepth().stream()
            .flatMap(i ->
              Stream.of(
                MemberRelationshipDepth.builder()
                  .depth(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .onlineTalkStyle(
          request.onlineTalkStyles().stream()
            .flatMap(i ->
              Stream.of(
                MemberOnlineTalkStyle.builder()
                  .onlineTalkStyle(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .isOfflineMeeting(
          request.offlineTalkStyles().stream()
            .flatMap(i ->
              Stream.of(
                MemberOfflineMeeting.builder()
                  .isOffline(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .interestPreference(
          request.interestPreference().stream()
            .flatMap(i ->
              Stream.of(
                MemberInterestPreference.builder()
                  .preference(i)
                  .member(member)
                  .build()
              )
            ).toList()
        )
        .interestLevel(
          request.interestLevel().stream()
            .flatMap(i ->
              Stream.of(
                MemberInterestLevel.builder()
                  .level(i)
                  .member(member)
                  .build()
              )
            ).toList()
        ).build()
    );
  }
}
