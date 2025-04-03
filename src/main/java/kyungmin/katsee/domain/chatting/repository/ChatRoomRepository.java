package kyungmin.katsee.domain.chatting.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.chatting.Chatting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kyungmin.katsee.domain.chatting.QChatting.chatting;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {
  private final JPAQueryFactory queryFactory;

  public void save(Chatting room) {
    queryFactory.insert(chatting)
      .columns(chatting.id, chatting.friend, chatting.member, chatting.createdAt)
      .values(room.getId(), room.getFriend(), room.getMember(), room.getCreatedAt())
      .execute();
  }

  public List<Chatting> findByMemberId(String memberId) {
    return queryFactory
      .selectFrom(chatting)
      .where(chatting.member.memberId.eq(memberId))
      .fetch();
  }

  public void delete(Long roomId) {
    queryFactory.delete(chatting)
      .where(chatting.id.eq(roomId))
      .execute();
  }
}
