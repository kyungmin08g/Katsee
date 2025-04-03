package kyungmin.katsee.domain.chatting.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.chatting.Chatting;
import kyungmin.katsee.domain.chatting.repository.ChatContentRepository;
import kyungmin.katsee.domain.chatting.repository.ChattingRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChattingRepository chatRoomRepository;
  private final ChatContentRepository chatContentRepository;
  private final MemberRepository memberRepository;

  public void createChatRoom(String friendId) {
    Member friend = memberRepository.findById(friendId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "친구를 찾을 수 없습니다."));
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    chatRoomRepository.save(
      Chatting.builder()
        .friend(friend)
        .member(member)
        .build()
    );
  }
}
