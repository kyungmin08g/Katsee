package kyungmin.katsee.domain.matching.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "매칭 통계 응답 객체")
public class GetMatchingStatisticsResponse {

  @Schema(description = "전체 매칭 통계")
  private int fullStatistics;

  @Schema(description = "신규 매칭 통계")
  private int newStatistics;
}
