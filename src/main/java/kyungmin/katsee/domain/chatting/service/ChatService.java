package kyungmin.katsee.domain.chatting.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.chatting.Chatting;
import kyungmin.katsee.domain.chatting.controller.response.GetChatRoomResponse;
import kyungmin.katsee.domain.chatting.repository.ChatContentRepository;
import kyungmin.katsee.domain.chatting.repository.ChatRoomRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {
  private final ChatRoomRepository roomRepository;
  private final ChatContentRepository chatContentRepository;
  private final MemberRepository memberRepository;

  public void createChatRoom(String friendId) {
    Member friend = memberRepository.findById(friendId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "친구를 찾을 수 없습니다."));
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    roomRepository.save(
      Chatting.builder()
        .friend(friend)
        .member(member)
        .createdAt(LocalDateTime.now())
        .build()
    );
  }

  public List<GetChatRoomResponse> getChatRooms() {
    List<GetChatRoomResponse> chatRooms = new ArrayList<>();
    List<Chatting> rooms = roomRepository.findByMemberId(SecurityUtil.authMemberId());
    rooms.forEach(room -> {
      chatRooms.add(
        GetChatRoomResponse.builder()
          .roomId(room.getId())
          .friendId(room.getFriend().getMemberId())
          .createdAt(room.getCreatedAt())
          .build()
      );
    });

    return chatRooms;
  }
}
