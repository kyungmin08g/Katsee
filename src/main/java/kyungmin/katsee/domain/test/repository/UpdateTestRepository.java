package kyungmin.katsee.domain.test.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.test.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kyungmin.katsee.domain.test.QTestEntity.testEntity;

@Repository
@RequiredArgsConstructor
public class UpdateTestRepository {
  private final JPAQueryFactory queryFactory;

  public void update(TestEntity entity) {
    queryFactory.update(testEntity)
      .where(testEntity.id.eq(entity.id))
      .set(testEntity.content, entity.content)
      .execute();
  }
}
