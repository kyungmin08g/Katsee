package kyungmin.katsee.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.member.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원 상세 정보 조회 응답 객체")
public class GetMemberDetailResponse {

  @Schema(description = "회원 ID")
  private String memberId;

  @Schema(description = "프로필 사진")
  private String profileUrl;

  @Schema(description = "닉네임")
  private String nickName;

  @Schema(description = "나이")
  private String age;

  @Schema(description = "성별")
  private Gender gender;

  @Schema(description = "소개")
  private String introduction;

  @Schema(description = "관심사 유형")
  private List<Interest> interests;

  @Schema(description = "성격 유형")
  private List<PersonalityType> personalityTypes;

  @Schema(description = "대화 스타일")
  private List<TalkStyle> talkStyles;

  @Schema(description = "친구 스타일")
  private List<FriendStyle> friendStyles;

  @Schema(description = "관계 깊이 정도")
  private List<RelationshipDepth> relationshipDepth;

  @Schema(description = "온라인 대화 스타일")
  private List<OnlineTalkStyle> onlineTalkStyles;

  @Schema(description = "오프라인 만남 가능 여부")
  private List<OfflineMeeting> offlineTalkStyles;

  @Schema(description = "관심사 선호도")
  private List<InterestPreference> interestPreference;

  @Schema(description = "관심사 레벨")
  private List<InterestLevel> interestLevel;
}
