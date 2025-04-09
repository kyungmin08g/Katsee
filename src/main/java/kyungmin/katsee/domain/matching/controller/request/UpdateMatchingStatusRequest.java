package kyungmin.katsee.domain.matching.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kyungmin.katsee.domain.matching.enums.MatchStatus;

@Schema(description = "매칭 상태 변경 요청 객체")
public record UpdateMatchingStatusRequest(

  @Schema(description = "친구 ID")
  String friendId,

  @Schema(description = "매칭 상태")
  MatchStatus status
) { }
