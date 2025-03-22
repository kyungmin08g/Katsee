package kyungmin.katsee.domain.enum_api.service;

import kyungmin.katsee.domain.enum_api.controller.response.GetEnumResponse;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import kyungmin.katsee.domain.member.enums.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class EnumService {

  // 매칭 상태 조회
  public List<GetEnumResponse> getMatchingStatusEnums() {
    return Stream.of(MatchStatus.values())
      .flatMap(matchingStatus ->
        Stream.of( // GetEnumResponse 객체를 가진 Stream 생성
          GetEnumResponse.builder()
            .code(matchingStatus.name())
            .value(matchingStatus.getValue())
            .build()
        )
      )
      .toList();
  }

  // 친구 스타일 조회
  public List<GetEnumResponse> getFriendStyleEnums() {
    return Stream.of(FriendStyle.values())
      .flatMap(friendStyle ->
        Stream.of(
          GetEnumResponse.builder()
            .code(friendStyle.name())
            .value(friendStyle.getValue())
            .build()
        )
      )
      .toList();
  }

  // 관심사 유형 조회
  public List<GetEnumResponse> getInterestEnums() {
    return Stream.of(Interest.values())
      .flatMap(interest ->
        Stream.of(
          GetEnumResponse.builder()
            .code(interest.name())
            .value(interest.getValue())
            .build()
        )
      )
      .toList();
  }

  // 관심사 레벨 조회
  public List<GetEnumResponse> getInterestLevelEnums() {
    return Stream.of(InterestLevel.values())
      .flatMap(level ->
        Stream.of(
          GetEnumResponse.builder()
            .code(level.name())
            .value(level.getValue())
            .build()
        )
      )
      .toList();
  }

  // 관심사 선호도 조회
  public List<GetEnumResponse> getInterestPreferenceEnums() {
    return Stream.of(InterestPreference.values())
      .flatMap(preference ->
        Stream.of(
          GetEnumResponse.builder()
            .code(preference.name())
            .value(preference.getValue())
            .build()
        )
      )
      .toList();
  }

  // 오프라인 만남 허용 여부 조회
  public List<GetEnumResponse> getOfflineMeetingEnums() {
    return Stream.of(OfflineMeeting.values())
      .flatMap(offlineMeeting ->
        Stream.of(
          GetEnumResponse.builder()
            .code(offlineMeeting.name())
            .value(offlineMeeting.getValue())
            .build()
        )
      )
      .toList();
  }

  // 온라인 대화 스타일 조회
  public List<GetEnumResponse> getOnlineTalkStyleEnums() {
    return Stream.of(OnlineTalkStyle.values())
      .flatMap(onlineTalkStyle ->
        Stream.of(
          GetEnumResponse.builder()
            .code(onlineTalkStyle.name())
            .value(onlineTalkStyle.getValue())
            .build()
        )
      )
      .toList();
  }

  // 성격 유형 조회
  public List<GetEnumResponse> getPersonalityTypeEnums() {
    return Stream.of(PersonalityType.values())
      .flatMap(personalityType ->
        Stream.of(
          GetEnumResponse.builder()
            .code(personalityType.name())
            .value(personalityType.getValue())
            .build()
        )
      )
      .toList();
  }

  // 관계 깊이 조회
  public List<GetEnumResponse> getRelationshipDepthEnums() {
    return Stream.of(RelationshipDepth.values())
      .flatMap(relationshipDepth ->
        Stream.of(
          GetEnumResponse.builder()
            .code(relationshipDepth.name())
            .value(relationshipDepth.getValue())
            .build()
        )
      )
      .toList();
  }

  // 대화 스타일 조회
  public List<GetEnumResponse> getTalkStyleEnums() {
    return Stream.of(TalkStyle.values())
      .flatMap(talkStyle ->
        Stream.of(
          GetEnumResponse.builder()
            .code(talkStyle.name())
            .value(talkStyle.getValue())
            .build()
        )
      )
      .toList();
  }
}
