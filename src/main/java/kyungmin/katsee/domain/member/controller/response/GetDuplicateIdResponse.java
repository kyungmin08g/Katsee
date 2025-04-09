package kyungmin.katsee.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "회원 ID 중복 여부 조회 응답 객체")
public class GetDuplicateIdResponse {

  @Schema(description = "여부")
  private Boolean isDuplicate;

  @Schema(description = "메시지")
  private String message;
}
