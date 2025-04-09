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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecommendFriendService {
  private final RecommendFriendRepository recommendFriendRepository;
  private final MemberRepository memberRepository;

  // 친구 추천
  public List<GetRecommendFriendResponse> recommendFriends() {
    List<GetRecommendFriendResponse> recommendFriends = new ArrayList<>();
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    recommendFriendRepository.friends().forEach(friend -> {
      if (friend.getMemberId().equals(member.getMemberId())) return;
      if (friend.getMemberId().equals("admin")) return;
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
        .flatMap(f ->
          Stream.of(f.getInterest())
        ).toList();
      boolean isInterest = friend.getInterest().stream()
        .flatMap(f ->
          Stream.of(f.getInterest())
        ).toList().stream()
        .anyMatch(interests::contains);
      if (isInterest) fitness += 30;
      else fitness -= 25;

      // 관심사 선호도
      List<InterestPreference> interestPreferences = member.getInterestPreference().stream()
        .flatMap(f ->
          Stream.of(f.getPreference())
        ).toList();
      boolean isInterestPreferences = friend.getInterestPreference().stream()
        .flatMap(f ->
          Stream.of(f.getPreference())
        ).toList().stream()
        .anyMatch(interestPreferences::contains);
      if (isInterestPreferences) fitness += 14;
      else  if (fitness != 0) fitness -= 3;

      // 관심사 레벨
      List<InterestLevel> interestLevel = member.getInterestLevel().stream()
        .flatMap(f ->
          Stream.of(f.getLevel())
        ).toList();
      boolean isInterestLevel = friend.getInterestLevel().stream()
        .flatMap(f ->
          Stream.of(f.getLevel())
        ).toList().stream()
        .anyMatch(interestLevel::contains);
      if (isInterestLevel) fitness += 12;
      else  if (fitness != 0) fitness -= 3;

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
      else  if (fitness != 0) fitness -= 3;

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
      else if (fitness != 0) fitness -= 3;

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
      else if (fitness != 0) fitness -= 3;

      // 오프라인 만남 가능 여부
      List<OfflineMeeting> offlineMeeting = member.getIsOfflineMeeting().stream()
        .flatMap(f ->
          Stream.of(f.getIsOffline())
        ).toList();
      boolean isOfflineMeeting = friend.getIsOfflineMeeting().stream()
        .flatMap(f ->
          Stream.of(f.getIsOffline())
        ).toList().stream()
        .anyMatch(offlineMeeting::contains);
      if (isOfflineMeeting) fitness += 4;
      else if (fitness != 0) fitness -= 3;

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
      else  if (fitness != 0) fitness -= 3;

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
      else  if (fitness != 0) fitness -= 3;

      // 적합도 50 이하는 추천 목록에서 제거
      if (fitness > 50) {
        recommendFriends.add(
          GetRecommendFriendResponse.builder()
            .fitness(fitness)
            .friendId(friend.getMemberId())
            .profileUrl(friend.getProfileUrl())
            .nickName(friend.getNickName())
            .age(friend.getAge())
            .gender(friend.getGender().value)
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
      fitness = 0;
    });

    // 적합도 순으로 정렬
    recommendFriends.sort((o1, o2) ->
      o2.getFitness().compareTo(o1.getFitness())
    );

    return recommendFriends;
  }
}
