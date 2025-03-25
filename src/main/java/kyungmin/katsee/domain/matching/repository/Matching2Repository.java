package kyungmin.katsee.domain.matching.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.matching.Matching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kyungmin.katsee.domain.matching.QMatching.matching;

@Repository
@RequiredArgsConstructor
public class Matching2Repository {
  private final JPAQueryFactory queryFactory;

  public List<Matching> findByMemberId(String memberId) {
    return queryFactory
      .select(matching)
      .from(matching)
      .where(matching.member.memberId.eq(memberId))
      .fetch();
  }
}
