package kyungmin.katsee.domain.chatting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.chatting.controller.request.SaveContentRequest;
import kyungmin.katsee.domain.chatting.controller.response.GetChatRoomResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetContentListResponse;
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

  // 채팅 내용 저장
  @PostMapping(value = "/content/save")
  @Operation(description = "채팅 내용 저장")
  public ApiResponse<Void> saveChatting(@RequestBody SaveContentRequest request) {
    chatService.saveChattingContent(request);
    return ApiResponse.onSuccess();
  }

  // 채팅 내용 조회
  @GetMapping(value = "/content")
  @Operation(description = "채팅 내용 목록 조회")
  public ApiResponse<List<GetContentListResponse>> getChatContent(@RequestParam String roomId) {
    return ApiResponse.onSuccess(chatService.getChatContentList(Long.parseLong(roomId)));
  }
}
