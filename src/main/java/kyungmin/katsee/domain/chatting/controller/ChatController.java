package kyungmin.katsee.domain.chatting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetChatRoomResponse;
import kyungmin.katsee.domain.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
@Tag(name = "채팅 관련 API")
public class ChatController {
  private final ChatService chatService;

  // 채팅방 생성
  @PostMapping(value = "/create")
  @Operation(description = "채팅방 생성")
  public ApiResponse<?> createChatRoom(@RequestParam String friendId) {
    chatService.createChatRoom(friendId);
    return ApiResponse.onSuccess();
  }

  // 채팅방 목록 조회
  @GetMapping(value = "/rooms")
  @Operation(description = "채팅방 목록 조회")
  public ApiResponse<List<GetChatRoomResponse>> getChatRooms() {
    return ApiResponse.onSuccess(chatService.getChatRooms());
  }

  // 채팅방 삭제
}
