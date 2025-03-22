package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.MemberInterest;
import kyungmin.katsee.domain.member.controller.request.MemberRegisterRequest;
import kyungmin.katsee.domain.member.controller.response.GetMember;
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

    Member member = memberRepository.findById(request.memberId()).orElseThrow();
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

}
