package kyungmin.katsee.domain.chatting.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.chatting.ChatContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kyungmin.katsee.domain.chatting.QChatContent.chatContent;

@Repository
@RequiredArgsConstructor
public class ChattingRepository {
  private final JPAQueryFactory queryFactory;

  public void save(ChatContent chat) {
    queryFactory.insert(chatContent)
      .columns(chatContent.id, chatContent.room, chatContent.member, chatContent.content, chatContent.createdAt)
      .values(chat.getId(), chat.getRoom(), chat.getMember(), chat.getContent(), chat.getCreatedAt())
      .execute();
  }

  public ChatContent findById(Long id) {
    return queryFactory
      .selectFrom(chatContent)
      .where(chatContent.id.eq(id))
      .fetchOne();
  }

  public void deleteById(Long roomId) {
    queryFactory.delete(chatContent)
      .where(chatContent.room.id.eq(roomId))
      .execute();
  }
}
