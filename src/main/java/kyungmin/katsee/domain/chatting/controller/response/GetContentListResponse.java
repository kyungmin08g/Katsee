package kyungmin.katsee.domain.chatting.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Schema(description = "채팅 내용 목록 조회 응답 객체")
@NoArgsConstructor
public class GetContentListResponse {

  @Schema(description = "채팅방 ID")
  private Long roomId;

  @Schema(description = "회원 ID")
  private String memberId;

  @Schema(description = "내용")
  private String content;
}
