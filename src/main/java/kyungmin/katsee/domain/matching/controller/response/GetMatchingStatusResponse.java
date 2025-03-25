package kyungmin.katsee.domain.matching.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "매칭 상태 응답 객체")
public class GetMatchingStatusResponse {

  @Schema(description = "대기 상태")
  private int atmosphereStatus;

  @Schema(description = "친구 상태")
  private int friendStatus;

  @Schema(description = "거절 상태")
  private int refusalStatus;
}
