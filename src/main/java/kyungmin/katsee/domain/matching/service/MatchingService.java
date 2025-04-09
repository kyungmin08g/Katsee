package kyungmin.katsee.domain.matching.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.matching.Matching;
import kyungmin.katsee.domain.matching.controller.request.UpdateMatchingStatusRequest;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatisticsResponse;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatusResponse;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import kyungmin.katsee.domain.matching.repository.MatchingRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.response.GetMemberResponse;
import kyungmin.katsee.domain.member.enums.Interest;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MatchingService {
  private final MatchingRepository matchingRepository;
  private final MemberRepository memberRepository;

  // 회원 조회
  private Member getMember(String memberId) {
    return memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
  }

  // 매칭 요청 보내기
  public void matching(String friendId) {
    matchingRepository.save(
      Matching.builder()
        .member(getMember(SecurityUtil.authMemberId()))
        .friend(getMember(friendId))
        .matchStatus(MatchStatus.ATMOSPHERE)
        .build()
    );
  }

  // 매칭 현황(통계) 조회
  public GetMatchingStatisticsResponse getMatchingStatistics() {
    AtomicInteger fullStatistics = new AtomicInteger();
    AtomicInteger newStatistics = new AtomicInteger();
    String now = LocalDateTime.now().toString().split("T")[0]; // 날짜만 얻기

    matchingRepository.findByMemberId(SecurityUtil.authMemberId()).forEach(matching -> {
      if (matching.getCreatedAt().toString().split("T")[0].equals(now)) newStatistics.getAndIncrement();
      fullStatistics.getAndIncrement();
    });

    return GetMatchingStatisticsResponse.builder()
      .fullStatistics(fullStatistics.get())
      .newStatistics(newStatistics.get())
      .build();
  }

  // 매칭 상태 조회
  public GetMatchingStatusResponse getMatchingStatus() {
    AtomicInteger atmosphere = new AtomicInteger();
    AtomicInteger friend = new AtomicInteger();
    AtomicInteger refusal = new AtomicInteger();

    matchingRepository.findByMemberId(SecurityUtil.authMemberId()).forEach(matching -> {
      switch (matching.getMatchStatus()) {
        case ATMOSPHERE: atmosphere.getAndIncrement(); break;
        case FRIEND: friend.getAndIncrement(); break;
        case REFUSE: refusal.getAndIncrement(); break;
      }
    });

    return GetMatchingStatusResponse.builder()
      .atmosphere(atmosphere.get())
      .friend(friend.get())
      .refusal(refusal.get())
      .build();
  }

  // 매칭 상태 수정
  public void updateMatchingStatus(UpdateMatchingStatusRequest request) {
    matchingRepository.update(request);
  }

  // 친구 조회
  public List<GetMemberResponse> getFriends() {
    List<GetMemberResponse> friends = new ArrayList<>();
    List<Matching> friend = matchingRepository.getFriends(SecurityUtil.authMemberId());
    List<Matching> member = matchingRepository.getFriendByFriendId(SecurityUtil.authMemberId());

    member.forEach(m -> {
      friends.add(
        GetMemberResponse.builder()
          .memberId(m.getMember().getMemberId())
          .profileUrl(m.getMember().getProfileUrl())
          .nickName(m.getMember().getNickName())
          .age(m.getMember().getAge())
          .gender(m.getMember().getGender().value)
          .introduction(m.getMember().getIntroduction())
          .interests(
            m.getMember().getInterest().stream()
              .flatMap(i ->
                Stream.of(Interest.values()).filter(f -> f.equals(i.getInterest()))
              ).toList()
          ).build()
      );
    });

    friend.forEach(matching -> {
      friends.add(
        GetMemberResponse.builder()
          .memberId(matching.getFriend().getMemberId())
          .profileUrl(matching.getFriend().getProfileUrl())
          .nickName(matching.getFriend().getNickName())
          .age(matching.getFriend().getAge())
          .gender(matching.getFriend().getGender().value)
          .introduction(matching.getFriend().getIntroduction())
          .interests(
            matching.getFriend().getInterest().stream()
              .flatMap(i ->
                Stream.of(Interest.values()).filter(f -> f.equals(i.getInterest()))
              ).toList()
          ).build()
      );
    });

    return friends;
  }

  // 친구 요청 보내기
  public List<GetMemberResponse> getRequestFriends() {
    List<GetMemberResponse> requestFriends = new ArrayList<>();
    List<Matching> request = matchingRepository.getRequestFriends();

    request.forEach(matching -> {
      if (!matching.getMember().getMemberId().equals(SecurityUtil.authMemberId())) {
        requestFriends.add(
          GetMemberResponse.builder()
            .memberId(matching.getMember().getMemberId())
            .profileUrl(matching.getMember().getProfileUrl())
            .nickName(matching.getMember().getNickName())
            .age(matching.getMember().getAge())
            .gender(matching.getMember().getGender().value)
            .introduction(matching.getMember().getIntroduction())
            .interests(
              matching.getMember().getInterest().stream()
                .flatMap(i ->
                  Stream.of(Interest.values()).filter(f -> f.equals(i.getInterest()))
                ).toList()
            ).build()
        );
      }
    });

    return requestFriends;
  }

  // 친구 상태인지 확인
  public boolean matchingStatusDuplicate(String friendId) {
    List<Matching> friend = matchingRepository.matchingStatusDuplicate(friendId);
    if (friend.isEmpty()) return false;

    AtomicBoolean duplicate = new AtomicBoolean(false);
    friend.forEach(matching -> {
      log.info("회원: {}, 친구: {}", matching.getFriend().getNickName(), matching.getMember().getNickName());

      if (matching.getFriend().getMemberId().equals(SecurityUtil.authMemberId())) {
        duplicate.set(true);
      } if (!matching.getFriend().getMemberId().equals(SecurityUtil.authMemberId())) {
        duplicate.set(true);
      } else if (matching.getFriend().getMemberId().equals(friendId)) {
        duplicate.set(true);
      } else if (!matching.getMember().getMemberId().equals(friendId)) {
        duplicate.set(false);
      }
    });

    return duplicate.get();
  }
}
