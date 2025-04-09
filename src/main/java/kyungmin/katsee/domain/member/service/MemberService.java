package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.matching.repository.MatchingRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.request.MemberCreateRequest;
import kyungmin.katsee.domain.member.controller.request.MemberDetailRequest;
import kyungmin.katsee.domain.member.controller.request.UpdateDetailRequest;
import kyungmin.katsee.domain.member.controller.response.GetAdminStatisticsResponse;
import kyungmin.katsee.domain.member.controller.response.GetDuplicateIdResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberDetailResponse;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.enums.Interest;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.*;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final MatchingRepository matchingRepository;

  private final CreateMemberService createService;
  private final DetailMemberService detailMemberService;

  // 회원 조회
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

  // 특정 회원 조회
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

  // 아이디 중복 확인
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

  // 회원 등록
  public void createMember(MemberCreateRequest request) {
    createService.createMember(request);
  }

  // 회원 상세 정보 등록
  public void createMemberDetail(MemberDetailRequest request) {
    createService.createMemberDetail(request);
  }

  // 회원 상세 정보 조회
  public GetMemberDetailResponse getMemberDetail(String memberId) {
    return detailMemberService.getMemberDetail(memberId);
  }

  // 회원 상세 정보 수정
  public void updateMemberDetail(UpdateDetailRequest request) {
    detailMemberService.updateMemberDetail(request);
  }

  // 모든 회원 목록 조회
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

  // 전체 사용자 & 주요 연령대 조회
  public GetAdminStatisticsResponse getAdminStatistics() {
    Member admin = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    AtomicInteger count = new AtomicInteger();
    Integer age = 0;
    List<Integer> ages = new ArrayList<>();
    if (admin.getRole().equals(Role.ADMIN)) { // 관리자만 접근 가능
      // 전체 사용자 구하기
      memberRepository.findAll().forEach(member -> {
        if (!member.getRole().equals(Role.ADMIN)) { // 관리자는 무시
          count.getAndIncrement();
          ages.add(Integer.parseInt(member.getAge()));
        }
      });

      // 주요 연령대 구하기
      Set<Integer> set = new HashSet<>(ages);
      Map<Integer, Integer> map = new HashMap<>();
      set.forEach(a -> {
        int sumCount = 0;
        for (int i = 0; i < ages.size(); i++) {
          if (ages.get(i).equals(a)) {
            sumCount++;
          }
        }
        map.put(sumCount, a);
      });

      Integer max = Arrays.stream(map.keySet().toArray(new Integer[0]))
        .mapToInt(v -> v)
        .max()
        .orElse(0);
      age = map.get(max);
    } else throw new GeneralException(ErrorStatus.UNAUTHORIZED, "관리자가 아닙니다.");

    return GetAdminStatisticsResponse.builder()
      .allUsers(count.get())
      .age(age)
      .build();
  }
}
