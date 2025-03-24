package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public GetMemberResponse getMember(String id) {
    List<String> interests = new ArrayList<>();

    Member member = memberRepository.findById(id)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
    member.getInterest().forEach(interest -> {
      interests.add(interest.getInterest().name());
    });

    return GetMemberResponse.builder()
      .memberId(member.getMemberId())
      .profileUrl(member.getProfileUrl())
      .nickName(member.getNickName())
      .age(member.getAge())
      .gender(member.getGender().name())
      .introduction(member.getIntroduction())
      .interests(interests)
      .build();
  }

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
}
