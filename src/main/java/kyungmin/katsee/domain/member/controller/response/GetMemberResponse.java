package kyungmin.katsee.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.member.enums.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "회원 조회 응답 객체")
public class GetMemberResponse {

  @Schema(description = "회원 ID")
  private String memberId;

  @Schema(description = "프로필 사진")
  private String profileUrl;

  @Schema(description = "닉네임")
  private String nickName;

  @Schema(description = "나이")
  private String age;

  @Schema(description = "성별")
  private String gender;

  @Schema(description = "소개")
  private String introduction;

  @Schema(description = "관심사 유형")
  private List<Interest> interests;
}
