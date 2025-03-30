package kyungmin.katsee.domain.notice.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@Schema(description = "공지 조회 응답 객체")
public class GetNoticeResponse {

  @Schema(description = "공지 ID")
  private Long id;

  @Schema(description = "썸네일 사진")
  private String thumbnailUrl;

  @Schema(description = "제목")
  private String title;

  @Schema(description = "내용")
  private String content;

  @Schema(description = "생성일")
  private LocalDateTime createdAt;
}
