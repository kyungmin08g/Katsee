package kyungmin.katsee.domain.chatting.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채팅 내용 저장 요청 객체")
public record SaveContentRequest(

  @Schema(description = "채팅방 ID")
  String roomId,

  @Schema(description = "채팅 내용")
  String chatContent
) { }
