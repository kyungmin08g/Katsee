package kyungmin.katsee.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import kyungmin.katsee.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kyungmin.katsee.domain.matching.QMatching.matching;
import static kyungmin.katsee.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class RecommendFriendRepository {
  private final JPAQueryFactory queryFactory;

  public List<Member> friends() {
    return queryFactory
      .selectFrom(member)
      .innerJoin(matching)
      .on(matching.matchStatus.ne(MatchStatus.REFUSE)) // 한번 거절한 건은 추천 X
      .fetch();
  }
}
