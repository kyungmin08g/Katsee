package kyungmin.katsee.domain.test.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kyungmin.katsee.domain.test.QTestEntity.testEntity;

@Repository
@RequiredArgsConstructor
public class DeleteTestRepository {
  private final JPAQueryFactory queryFactory;

  public void delete(Long id) {
    queryFactory.delete(testEntity)
      .where(testEntity.id.eq(id))
      .execute();
  }
}
