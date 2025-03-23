package kyungmin.katsee.domain.member.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.member.enums.*;

import java.util.List;

@Schema(description = "회원 상세 정보 등록 요청 객체")
public record MemberDetailsRequest(

  @Schema(description = "성격 유형")
  List<PersonalityType> personality,

  @Schema(description = "대화 스타일")
  List<TalkStyle> talkStyle,

  @Schema(description = "친구 스타일")
  List<FriendStyle> friendStyle,

  @Schema(description = "관계 깊이 정도")
  RelationshipDepth relationshipDepth,

  @Schema(description = "음성/영상 대화 가능 여부")
  List<OnlineTalkStyle> voiceOrVideoTalk,

  @Schema(description = "오프라인 만난 허용 여부")
  OfflineMeeting isOffline,

  @Schema(description = "관심사 선호도")
  InterestPreference interestPreferences,

  @Schema(description = "관심사 레벨")
  InterestLevel interestLevel
) { }
