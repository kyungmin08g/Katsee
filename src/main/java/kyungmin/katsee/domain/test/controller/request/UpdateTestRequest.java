package kyungmin.katsee.domain.test.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "테스트 업데이트 요청 객체")
public record UpdateTestRequest(
  @Schema(description = "아이디")
  String id,

  @Schema(description = "내용")
  String content
) { }
