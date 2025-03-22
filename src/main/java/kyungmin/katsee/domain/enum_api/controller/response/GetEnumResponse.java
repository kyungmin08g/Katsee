package kyungmin.katsee.domain.enum_api.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Enum 응답 객체")
public class GetEnumResponse {

  @Schema(description = "Enum 코드")
  private String code;

  @Schema(description = "Enum 값")
  private String value;
}
