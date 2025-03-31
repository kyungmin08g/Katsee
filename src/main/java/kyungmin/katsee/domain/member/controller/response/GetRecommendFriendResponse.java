package kyungmin.katsee.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.member.enums.Gender;
import kyungmin.katsee.domain.member.enums.Interest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@Schema(description = "친구 추천 응답 객체")
public class GetRecommendFriendResponse {

  @Schema(description = "적합도")
  private Integer fitness;

  @Schema(description = "친구 ID")
  private String friendId;

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
}
