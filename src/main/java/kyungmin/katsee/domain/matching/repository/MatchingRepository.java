package kyungmin.katsee.domain.matching.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.matching.Matching;
import kyungmin.katsee.domain.matching.controller.request.UpdateMatchingStatusRequest;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static kyungmin.katsee.domain.matching.QMatching.matching;

@Repository
@RequiredArgsConstructor
public class MatchingRepository {
  private final JPAQueryFactory queryFactory;

  public List<Matching> findByMemberId(String memberId) {
    return queryFactory
      .select(matching)
      .from(matching)
      .where(matching.member.memberId.eq(memberId))
      .fetch();
  }

  public void save(Matching matchingObject) {
    queryFactory.insert(matching)
      .columns(
        matching.member,
        matching.friend,
        matching.matchStatus,
        matching.createdAt
      ).values(
        matchingObject.getMember(),
        matchingObject.getFriend(),
        matchingObject.getMatchStatus(),
        LocalDateTime.now()
      ).execute();
  }

  public void update(UpdateMatchingStatusRequest request) {
    queryFactory.update(matching)
      .set(matching.matchStatus, request.status())
      .where(matching.member.memberId.eq(request.friendId()))
      .execute();
  }

  public List<Matching> getFriends(String memberId) {
    return queryFactory.select(matching)
      .from(matching)
      .where(
        matching.member.memberId.eq(memberId),
        matching.matchStatus.eq(MatchStatus.FRIEND)
      ).fetch();
  }

  public List<Matching> getFriendByFriendId(String memberId) {
    return queryFactory.select(matching)
      .from(matching)
      .where(
        matching.friend.memberId.eq(memberId),
        matching.matchStatus.eq(MatchStatus.FRIEND)
      ).fetch();
  }

  public List<Matching> getRequestFriends() {
    return queryFactory.selectFrom(matching)
      .where(matching.matchStatus.eq(MatchStatus.ATMOSPHERE))
      .fetch();
  }

  public List<Matching> matchingStatusDuplicate(String friendId) {
    return queryFactory.selectFrom(matching)
      .where(
        matching.friend.memberId.eq(friendId).or(matching.member.memberId.eq(friendId)),
        matching.matchStatus.eq(MatchStatus.FRIEND)
      ).fetch();
  }
}
