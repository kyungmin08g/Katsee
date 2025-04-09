package kyungmin.katsee.domain.chatting.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@Schema(description = "채팅방 목록 조회 응답 객체")
public class GetChatRoomResponse {

  @Schema(description = "채팅방 ID")
  private Long roomId;

  @Schema(description = "친구 ID")
  private String friendId;

  @Schema(description = "생성일")
  private LocalDateTime createdAt;
}
