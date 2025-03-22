package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.MemberInterest;
import kyungmin.katsee.domain.member.controller.request.MemberRegisterRequest;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.enums.Gender;
import kyungmin.katsee.domain.member.enums.Interest;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.MemberInterestRepository;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final MemberInterestRepository interestRepository;

  // 회원 등록
  public void registerMember(MemberRegisterRequest request) {
    List<MemberInterest> interests = new ArrayList<>();
    memberRepository.save(
      Member.builder()
        .memberId(request.memberId())
        .password(request.password())
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
      // 관심사 유형 저장
      MemberInterest memberInterest = MemberInterest.builder()
        .interest(Interest.valueOf(interest))
        .member(member)
        .build();
      interests.add(memberInterest);

      interestRepository.save(memberInterest);
    });

    memberRepository.save(
      member.toBuilder()
        .interest(interests)
        .build()
    );
  }

  // 회원 조회
  public GetMemberResponse getMember(String id) {
    List<String> interests = new ArrayList<>();

    Member member = memberRepository.findById(id)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
    member.getInterest().forEach(interest -> {
      interests.add(interest.getInterest().value);
    });

    return GetMemberResponse.builder()
      .memberId(member.getMemberId())
      .profileUrl(member.getProfileUrl())
      .nickName(member.getNickName())
      .age(member.getAge())
      .gender(member.getGender().value)
      .introduction(member.getIntroduction())
      .interests(interests)
      .build();
  }

  // 회원 ID 중복 여부
  public GetDuplicateIdResponse duplicateId(String memberId) {
    return (memberRepository.existsById(memberId)) ?
      GetDuplicateIdResponse.builder()
        .isDuplicate(true)
        .message("아이디가 중복됩니다.")
        .build() :
      GetDuplicateIdResponse.builder()
        .isDuplicate(false)
        .message("사용 가능한 아이디입니다.")
        .build();
  }

  // 회원 삭제
  public void deleteMember(String memberId) {
    memberRepository.deleteById(memberId);
  }
}
