package kyungmin.katsee.domain.matching.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.matching.Matching;
import kyungmin.katsee.domain.matching.controller.response.GetMatchingStatisticsResponse;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import kyungmin.katsee.domain.matching.repository.Matching2Repository;
import kyungmin.katsee.domain.matching.repository.MatchingRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class MatchingService {
  private final MatchingRepository matchingRepository;
  private final MemberRepository memberRepository;
  private final Matching2Repository matching2Repository;

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
    LocalDateTime now = LocalDateTime.now();

    matching2Repository.findByMemberId(
      SecurityUtil.authMemberId()
    ).forEach(matching -> {
      if (matching.getCreatedAt() == now) newStatistics.getAndIncrement();
      fullStatistics.getAndIncrement();
    });

    return GetMatchingStatisticsResponse.builder()
      .fullStatistics(fullStatistics.get())
      .newStatistics(newStatistics.get())
      .build();
  }
}
