package kyungmin.katsee.domain.test.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "테스트 상세 정보 응답 객체")
public class GetDetailsTestResponse {
  private String title;
  private String content;
}
