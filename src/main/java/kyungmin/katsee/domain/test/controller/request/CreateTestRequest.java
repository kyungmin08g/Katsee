package kyungmin.katsee.domain.test.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "테스트 등록 응답 객체")
public record CreateTestRequest(
  @Schema(description = "제목")
  String title,

  @Schema(description = "내용")
  String content
) { }
