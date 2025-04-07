package kyungmin.katsee.domain.chatting.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.chatting.ChatContent;
import kyungmin.katsee.domain.chatting.controller.response.GetContentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kyungmin.katsee.domain.chatting.QChatContent.chatContent;
import static kyungmin.katsee.domain.chatting.QChatting.chatting;

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

  public List<GetContentListResponse> findByRoom(Long roomId) {
    return queryFactory
      .select(
        Projections.fields(
          GetContentListResponse.class,
          chatContent.room.id.as("roomId"),
          chatContent.member.memberId,
          chatContent.content
        )
      ).from(chatContent)
      .where(chatContent.room.id.eq(roomId))
      .fetch();
  }

  public void deleteById(Long roomId) {
    queryFactory.delete(chatContent)
      .where(chatContent.room.id.eq(roomId))
      .execute();
  }
}
