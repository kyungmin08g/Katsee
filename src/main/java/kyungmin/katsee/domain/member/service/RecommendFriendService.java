package kyungmin.katsee.domain.member.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.controller.response.GetRecommendFriendResponse;
import kyungmin.katsee.domain.member.enums.*;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.member.repository.RecommendFriendRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecommendFriendService {
  private final RecommendFriendRepository recommendFriendRepository;
  private final MemberRepository memberRepository;

  public List<GetRecommendFriendResponse> recommendFriends() {
    List<GetRecommendFriendResponse> recommendFriends = new ArrayList<>();
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    recommendFriendRepository.friends().forEach(friend -> {
      int fitness = 0;
      /*
       🤔 최고점 30점 | 총 100점
       * !가중치 설정!
       - 관심사 유형 30
       - 성격 유형 12
       - 대화 스타일 10
       - 친구 스타일 13
       - 관계 깊이 정도 2
       - 온라인 대화 가능 여부 3
       - 오프라인 만남 가능 여부 4
       - 관심사 선호도 초2 중6 고14
       - 관심사 레벨 초2 중6 고12
       */

      // 관심사 유형
      List<Interest> interests = member.getInterest().stream()
        .flatMap(f -> Stream.of(f.getInterest()))
        .toList();
      boolean isInterest = friend.getInterest().stream()
        .flatMap(f ->
          Stream.of(f.getInterest())
        ).toList().stream()
        .anyMatch(interests::contains);
      if (isInterest) fitness += 30;
      else fitness -= 5;

      // 관심사 선호도
      List<InterestPreference> interestPreferences = member.getInterestPreference().stream()
        .flatMap(f ->
          Stream.of(f.getPreference())
        ).toList();
      switch (interestPreferences.get(0)) {
        case LOW: fitness += 2; break;
        case GENERALLY: fitness += 6; break;
        case POWERFUL: fitness += 14; break;
      }

      // 관심사 레벨
      List<InterestLevel> interestLevel = member.getInterestLevel().stream()
        .flatMap(f ->
          Stream.of(f.getLevel())
        ).toList();
      switch (interestLevel.get(0)) {
        case BEGINNER: fitness += 2; break;
        case INTERMEDIATE: fitness += 6; break;
        case MASTER: fitness += 12; break;
      }

      // 친구 스타일
      List<FriendStyle> friendStyle = member.getFriendStyle().stream()
        .flatMap(f ->
          Stream.of(f.getFriendStyle())
        ).toList();
      boolean isFriendStyle = friend.getFriendStyle().stream()
        .flatMap(f ->
          Stream.of(f.getFriendStyle())
        ).toList().stream()
        .anyMatch(friendStyle::contains);
      if (isFriendStyle) fitness += 13;
      else fitness -= 5;

      // 성격 유형
      List<PersonalityType> personalityType = member.getPersonalityType().stream()
        .flatMap(f ->
          Stream.of(f.getPersonality())
        ).toList();
      boolean isPersonalityType = friend.getPersonalityType().stream()
        .flatMap(f ->
          Stream.of(f.getPersonality())
        ).toList().stream()
        .anyMatch(personalityType::contains);
      if (isPersonalityType) fitness += 12;
      else fitness -= 5;

      // 대화 스타일
      List<TalkStyle> talkStyle = member.getTalkStyle().stream()
        .flatMap(f ->
          Stream.of(f.getTalkStyle())
        ).toList();
      boolean isTalkStyle = friend.getTalkStyle().stream()
        .flatMap(f ->
          Stream.of(f.getTalkStyle())
        ).toList().stream()
        .anyMatch(talkStyle::contains);
      if (isTalkStyle) fitness += 10;
      else fitness -= 5;

      // 오프라인 만남 가능 여부
      List<OfflineMeeting> offlineMeeting = member.getIsOfflineMeeting().stream()
        .flatMap(f ->
          Stream.of(f.getIsOffline())
        ).toList();
      switch (offlineMeeting.get(0)) {
        case ONLINE: fitness -= 5; break;
        case OFFLINE: fitness += 4; break;
      }

      // 온라인 대화 가능 여부
      List<OnlineTalkStyle> onlineTalkStyle = member.getOnlineTalkStyle().stream()
        .flatMap(f ->
          Stream.of(f.getOnlineTalkStyle())
        ).toList();
      boolean isOnlineTalkStyle = friend.getOnlineTalkStyle().stream()
        .flatMap(f ->
          Stream.of(f.getOnlineTalkStyle())
        ).toList().stream()
        .anyMatch(onlineTalkStyle::contains);
      if (isOnlineTalkStyle) fitness += 3;
      else fitness -= 5;

      // 관계 깊이 정도
      List<RelationshipDepth> relationshipDepth = member.getRelationshipDepth().stream()
        .flatMap(f ->
          Stream.of(f.getDepth())
        ).toList();
      boolean isRelationshipDepth = friend.getRelationshipDepth().stream()
        .flatMap(f ->
          Stream.of(f.getDepth())
        ).toList().stream()
        .anyMatch(relationshipDepth::contains);
      if (isRelationshipDepth) fitness += 2;
      else fitness -= 5;

      // 적합도 60 이하는 추천 목록에서 제거
      if (fitness > 60) {
        recommendFriends.add(
          GetRecommendFriendResponse.builder()
            .fitness(fitness)
            .friendId(friend.getMemberId())
            .profileUrl(friend.getProfileUrl())
            .nickName(friend.getNickName())
            .age(friend.getAge())
            .gender(friend.getGender())
            .introduction(friend.getIntroduction())
            .interests(
              friend.getInterest().stream()
                .flatMap(i ->
                  Stream.of(Interest.valueOf(i.getInterest().name()))
                )
                .toList()
            ).build()
        );
      }
    });

    return recommendFriends;
  }
}
