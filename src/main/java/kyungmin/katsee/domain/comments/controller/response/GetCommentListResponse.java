package kyungmin.katsee.domain.comments.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@Schema(description = "댓글 목록 응답 객체")
public class GetCommentListResponse {

  @Schema(description = "댓글 ID")
  private Long id;

  @Schema(description = "회원 ID")
  private String memberId;

  @Schema(description = "내용")
  private String content;

  @Schema(description = "생성일")
  private LocalDateTime createdAt;
}
