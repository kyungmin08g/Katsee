package kyungmin.katsee.domain.member.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.member.enums.*;

import java.util.List;

@Schema(description = "회원 정보 수정 요청 객체")
public record UpdateMemberDetailRequest(

  @Schema(description = "프로필 사진")
  String profileUrl,

  @Schema(description = "닉네임")
  String nickName,

  @Schema(description = "나이")
  String age,

  @Schema(description = "성별")
  Gender gender,

  @Schema(description = "소개")
  String introduction,

  @Schema(description = "관심사 유형")
  List<Interest> interests,

  @Schema(description = "성격 유형")
  List<PersonalityType> personalityTypes,

  @Schema(description = "대화 스타일")
  List<TalkStyle> talkStyles,

  @Schema(description = "친구 스타일")
  List<FriendStyle> friendStyles,

  @Schema(description = "관계 깊이 정도")
  List<RelationshipDepth> relationshipDepth,

  @Schema(description = "온라인 대화 스타일")
  List<OnlineTalkStyle> onlineTalkStyles,

  @Schema(description = "오프라인 만남 가능 여부")
  List<OfflineMeeting> offlineTalkStyles,

  @Schema(description = "관심사 선호도")
  List<InterestPreference> interestPreference,

  @Schema(description = "관심사 레벨")
  List<InterestLevel> interestLevel
) { }
