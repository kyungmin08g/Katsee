package kyungmin.katsee.domain.comments.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글 수정 요청 객체")
public record UpdateCommentRequest(

  @Schema(description = "댓글 ID")
  String commentId,

  @Schema(description = "공지 ID")
  String noticeId,

  @Schema(description = "내용")
  String content
) { }
