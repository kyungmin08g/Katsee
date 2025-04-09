package kyungmin.katsee.domain.chatting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.chatting.controller.request.SaveContentRequest;
import kyungmin.katsee.domain.chatting.controller.response.GetChatResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetChatRoomResponse;
import kyungmin.katsee.domain.chatting.controller.response.GetContentListResponse;
import kyungmin.katsee.domain.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

  /**
   * 채팅방 생성
   * @param friendId : 채팅방에 있는 친구 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/create")
  @Operation(description = "채팅방 생성")
  public ApiResponse<Void> createChatRoom(@RequestParam String friendId) {
    chatService.createChatRoom(friendId);
    return ApiResponse.onSuccess();
  }

  /**
   * 채팅방 목록 조회 - 채팅 네비게이션바 클릭시 호출
   * @return : 성공시 채팅방 목록 반환
   */
  @GetMapping(value = "/rooms")
  @Operation(description = "채팅방 목록 조회")
  public ApiResponse<List<GetChatRoomResponse>> getChatRooms() {
    return ApiResponse.onSuccess(chatService.getChatRooms());
  }

  /**
   * 채팅방 삭제 - 삭제 버튼 누를시 호출
   * @param roomId : 채팅방 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @DeleteMapping(value = "/delete")
  @Operation(description = "채팅방 삭제")
  public ApiResponse<Void> deleteChatRoom(@RequestParam String roomId) {
    chatService.deleteChatRoom(Long.parseLong(roomId));
    return ApiResponse.onSuccess();
  }

  /**
   * 메사지 내용 저장 - /send/{roomId}/{memberId} 경로로 들어올시 호출
   * @param roomId : 채팅방 아이디
   * @param memberId : 메시지 보내는 회원 아이디
   * @param request : 메시지 내용 저장 요청 객체
   * @return : 성공시 채팅방을 구독하고 있는 친구에게 메시지 전송 및 성공 JSON 반환
   */
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

  /**
   * 채팅 내용 목록 - 채팅방에 채팅 내용 목록을 조회 (채팅방에 들어올시 호출)
   * @param roomId : 채팅방 아이디
   * @return : 성공시 채팅 내용 목록 반환
   */
  @GetMapping(value = "/content/list")
  @Operation(description = "채팅 내용 목록 조회")
  public ApiResponse<List<GetContentListResponse>> getChatContent(@RequestParam String roomId) {
    return ApiResponse.onSuccess(chatService.getChatContentList(Long.parseLong(roomId)));
  }
}
