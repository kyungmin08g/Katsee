package kyungmin.katsee.domain.test.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.test.controller.response.GetDetailsTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kyungmin.katsee.domain.test.QTestEntity.testEntity;

@Repository
@RequiredArgsConstructor
public class DetailsTestRepository {
  private final JPAQueryFactory queryFactory;

  public GetDetailsTestResponse details(Long id) {
    return queryFactory
      .select(
        Projections.fields(
          GetDetailsTestResponse.class,
          testEntity.title,
          testEntity.content
        )
      ).from(testEntity)
      .where(testEntity.id.eq(id))
      .fetchOne();
  }
}
