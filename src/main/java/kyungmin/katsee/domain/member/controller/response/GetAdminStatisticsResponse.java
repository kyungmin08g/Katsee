package kyungmin.katsee.domain.member.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Schema(description = "전체 사용자 & 주요 연령대 조회 응답 객체")
public class GetAdminStatisticsResponse {

  @Schema(description = "전체 사용자")
  private int allUsers;

  @Schema(description = "주요 연령대")
  private int age;
}
