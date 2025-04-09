package kyungmin.katsee.domain.test.service;

import jakarta.transaction.Transactional;
import kyungmin.katsee.domain.test.TestEntity;
import kyungmin.katsee.domain.test.controller.request.CreateTestRequest;
import kyungmin.katsee.domain.test.controller.response.GetDetailsTestResponse;
import kyungmin.katsee.domain.test.controller.response.GetDetailTestList;
import kyungmin.katsee.domain.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
  private final CreateTestRepository createTestRepository;
  private final DetailsTestRepository detailsTestRepository;
  private final ListDetailTestRepository listDetailTestRepository;
  private final UpdateTestRepository updateTestRepository;
  private final DeleteTestRepository deleteTestRepository;

  // 생성
  public void create(CreateTestRequest request) {
    createTestRepository.create(request);
  }

  // 상세 조회
  public GetDetailsTestResponse details(Long id) {
    return detailsTestRepository.details(id);
  }

  // 상세 목록 조회
  public List<GetDetailTestList> listDetail(Long id) {
    return listDetailTestRepository.listDetail(id);
  }

  // 수정
  public void update(TestEntity entity) {
    updateTestRepository.update(entity);
  }

  // 삭제
  public void delete(Long id) {
    deleteTestRepository.delete(id);
  }
}
