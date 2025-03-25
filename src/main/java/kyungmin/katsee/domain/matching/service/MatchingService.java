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
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {
  private final MatchingRepository matchingRepository;
  private final MemberRepository memberRepository;

  private Member getMember(String memberId) {
    return memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
  }

  public void matching(String friendId) {
    matchingRepository.save(
      Matching.builder()
        .member(getMember(SecurityUtil.authMemberId()))
        .friend(getMember(friendId))
        .matchStatus(MatchStatus.ATMOSPHERE)
        .build()
    );
  }

  public GetMatchingStatisticsResponse getMatchingStatistics() {
    AtomicInteger fullStatistics = new AtomicInteger();
    AtomicInteger newStatistics = new AtomicInteger();
    String now = LocalDateTime.now().toString().split("T")[0];

    matchingRepository.findByMemberId(SecurityUtil.authMemberId()).forEach(matching -> {
      if (matching.getCreatedAt().toString().split("T")[0].equals(now)) newStatistics.getAndIncrement();
      fullStatistics.getAndIncrement();
    });

    return GetMatchingStatisticsResponse.builder()
      .fullStatistics(fullStatistics.get())
      .newStatistics(newStatistics.get())
      .build();
  }

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

  public void updateMatchingStatus(UpdateMatchingStatusRequest request) {
    matchingRepository.update(SecurityUtil.authMemberId(), request);
  }
}
