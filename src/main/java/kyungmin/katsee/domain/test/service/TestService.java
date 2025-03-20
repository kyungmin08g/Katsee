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

  public void create(CreateTestRequest request) {
    createTestRepository.create(request);
  }

  public GetDetailsTestResponse details(Long id) {
    return detailsTestRepository.details(id);
  }

  public List<GetDetailTestList> listDetail(Long id) {
    return listDetailTestRepository.listDetail(id);
  }

  public void update(TestEntity entity) {
    updateTestRepository.update(entity);
  }

  public void delete(Long id) {
    deleteTestRepository.delete(id);
  }
}
