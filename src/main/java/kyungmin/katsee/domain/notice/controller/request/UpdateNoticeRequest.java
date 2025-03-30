package kyungmin.katsee.domain.notice.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공지 수정 요청 객체")
public record UpdateNoticeRequest(

  @Schema(description = "공지 ID")
  String id,

  @Schema(description = "썸네일 사진")
  String thumbnail_url,

  @Schema(description = "제목")
  String title,

  @Schema(description = "내용")
  String content
) { }
