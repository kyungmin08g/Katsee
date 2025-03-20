package kyungmin.katsee.domain.test.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.test.controller.response.GetDetailTestList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kyungmin.katsee.domain.test.QTestEntity.testEntity;

@Repository
@RequiredArgsConstructor
public class ListDetailTestRepository {
  private final JPAQueryFactory queryFactory;

  public List<GetDetailTestList> listDetail(Long id) {
    return queryFactory
      .select(
        Projections.fields(
          GetDetailTestList.class,
          testEntity.id,
          testEntity.title,
          testEntity.content
        )
      ).from(testEntity)
      .where(testEntity.id.eq(id))
      .fetch();
  }
}
