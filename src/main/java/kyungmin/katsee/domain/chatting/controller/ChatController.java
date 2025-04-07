package kyungmin.katsee.domain.chatting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.chatting.controller.request.SaveContentRequest;
import kyungmin.katsee.domain.chatting.controller.response.GetChatResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetChatRoomResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetContentListResponse;
import kyungmin.katsee.domain.chatting.service.ChatService;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
@Tag(name = "채팅 관련 API")
public class ChatController {
  private final ChatService chatService;
  private final SimpMessagingTemplate messagingTemplate;

  @PostMapping(value = "/create")
  @Operation(description = "채팅방 생성")
  public ApiResponse<Void> createChatRoom(@RequestParam String friendId) {
    chatService.createChatRoom(friendId);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/rooms")
  @Operation(description = "채팅방 목록 조회")
  public ApiResponse<List<GetChatRoomResponse>> getChatRooms() {
    return ApiResponse.onSuccess(chatService.getChatRooms());
  }

  @DeleteMapping(value = "/delete")
  @Operation(description = "채팅방 삭제")
  public ApiResponse<Void> deleteChatRoom(@RequestParam String roomId) {
    chatService.deleteChatRoom(Long.parseLong(roomId));
    return ApiResponse.onSuccess();
  }

  @MessageMapping(value = "/{roomId}/{memberId}")
  public ApiResponse<Void> saveChatContent(@DestinationVariable("roomId") String roomId, @DestinationVariable("memberId") String memberId, SaveContentRequest request) {
    chatService.saveChattingContent(request, memberId);
    messagingTemplate.convertAndSend(
      "/sub/room/" + roomId,
      GetChatResponse.builder()
        .roomId(Long.parseLong(request.roomId()))
        .memberId(memberId)
        .content(request.chatContent())
        .build()
    );

    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/content/list")
  @Operation(description = "채팅 내용 목록 조회")
  public ApiResponse<List<GetContentListResponse>> getChatContent(@RequestParam String roomId) {
    return ApiResponse.onSuccess(chatService.getChatContentList(Long.parseLong(roomId)));
  }
}
