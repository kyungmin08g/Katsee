package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.*;
import kyungmin.katsee.domain.member.controller.request.MemberDetailsRequest;
import kyungmin.katsee.domain.member.controller.request.MemberRegisterRequest;
import kyungmin.katsee.domain.member.enums.Gender;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.*;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateMemberService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final MemberInterestRepository interestRepository;

  public void createMember(MemberRegisterRequest request) {
    memberRepository.save(
      Member.builder()
        .memberId(request.memberId())
        .password(passwordEncoder.encode(request.password()))
        .profileUrl(request.profileUrl())
        .nickName(request.nickName())
        .age(request.age())
        .gender(Gender.valueOf(request.gender()))
        .introduction(request.introduction())
        .role(Role.USER)
        .build()
    );

    Member member = memberRepository.findById(request.memberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    request.interests().forEach(interest -> {
      interestRepository.save(
        MemberInterest.builder()
          .interest(interest)
          .member(member)
          .build()
      );
    });
  }

  public void createMemberDetails(MemberDetailsRequest request) {
    String memberId = SecurityUtil.authMemberId();
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    request.personality().forEach(personality -> {
      member.getPersonalityType().add(
        MemberPersonalityType.builder()
          .personality(personality)
          .member(member)
          .build()
      );
    });

    request.talkStyle().forEach(talkStyle -> {
      member.getTalkStyle().add(
        MemberTalkStyle.builder()
          .talkStyle(talkStyle)
          .member(member)
          .build()
      );
    });

    request.friendStyle().forEach(friendStyle -> {
      member.getFriendStyle().add(
        MemberFriendStyle.builder()
          .friendStyle(friendStyle)
          .member(member)
          .build()
      );
    });

    request.voiceOrVideoTalk().forEach(online -> {
      member.getOnlineTalkStyle().add(
        MemberOnlineTalkStyle.builder()
          .onlineTalkStyle(online)
          .member(member)
          .build()
      );
    });

    member.getRelationshipDepth().add(
      MemberRelationshipDepth.builder()
        .depth(request.relationshipDepth())
        .member(member)
        .build()
    );

    member.getIsOfflineMeeting().add(
      MemberOfflineMeeting.builder()
        .isOffline(request.isOffline())
        .member(member)
        .build()
    );

    member.getInterestPreference().add(
      MemberInterestPreference.builder()
        .preference(request.interestPreferences())
        .member(member)
        .build()
    );

    member.getInterestLevel().add(
      MemberInterestLevel.builder()
        .level(request.interestLevel())
        .member(member)
        .build()
    );
  }
}
