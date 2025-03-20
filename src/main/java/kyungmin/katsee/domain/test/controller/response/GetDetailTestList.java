package kyungmin.katsee.domain.test.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "테스트 상세 조회 응답 객체")
public class GetDetailTestResponse {
  private int id;
  private String title;
  private String content;
}
