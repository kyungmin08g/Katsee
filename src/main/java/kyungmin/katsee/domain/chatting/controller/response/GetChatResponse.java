package kyungmin.katsee.domain.chatting.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Schema(description = "채팅 내용 응답 객체")
public class GetChatResponse {

  @Schema(description = "채팅방 ID")
  private Long roomId;

  @Schema(description = "회원 ID")
  private String memberId;

  @Schema(description = "내용")
  private String content;
}
