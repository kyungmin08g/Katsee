package kyungmin.katsee.domain.test.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.test.controller.request.CreateTestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kyungmin.katsee.domain.test.QTestEntity.testEntity;

@Repository
@RequiredArgsConstructor
public class CreateTestRepository {
  private final JPAQueryFactory queryFactory;

  public void create(CreateTestRequest request) {
    queryFactory.insert(testEntity)
      .columns(
        testEntity.title,
        testEntity.content
      ).values(request.title(), request.content())
      .execute();
  }
}
