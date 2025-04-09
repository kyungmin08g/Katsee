package kyungmin.katsee.domain.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.test.TestEntity;
import kyungmin.katsee.domain.test.controller.request.CreateTestRequest;
import kyungmin.katsee.domain.test.controller.request.UpdateTestRequest;
import kyungmin.katsee.domain.test.controller.response.GetDetailTestList;
import kyungmin.katsee.domain.test.controller.response.GetDetailsTestResponse;
import kyungmin.katsee.domain.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 생성 관련 : CreateXXXRequest
 * 상세 조회 관련 : getXXXResponse
 * 목록 조회 관련 : getXXXListResponse
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "테스트 관련 API")
public class TestController {
  public final TestService testService;

  /**
   * 리소스 생성 테스트
   * @param request : 테스트 등록 응답 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/create")
  @Operation(description = "리소스 생성 테스트")
  public ApiResponse<Void> create(@RequestBody CreateTestRequest request) {
    testService.create(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 리소스 조회 테스트
   * @param id : 아이디
   * @return : 성공시 테스트 상세 정보 응답 객체 반환
   */
  @GetMapping(value = "/detail/{id}")
  @Operation(description = "리소스 조회 테스트")
  public ApiResponse<GetDetailsTestResponse> details(@PathVariable("id") String id) {
    return ApiResponse.onSuccess(testService.details(Long.valueOf(id)));
  }

  /**
   * 리소스 상세 목록 조회 테스트
   * @param id : 아이디
   * @return : 성공시 테스트 상세 목록 조회 응답 객체 반환
   */
  @GetMapping(value = "/detail/list/{id}")
  @Operation(description = "리소스 목록 조회 테스트")
  public ApiResponse<List<GetDetailTestList>> detailList(@PathVariable("id") String id) {
    return ApiResponse.onSuccess(testService.listDetail(Long.valueOf(id)));
  }

  /**
   * 리소스 수정 테스트
   * @param request : 테스트 수정 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PatchMapping(value = "/update")
  @Operation(description = "리소스 수정 테스트")
  public ApiResponse<Void> update(@RequestBody UpdateTestRequest request) {
    TestEntity testEntity = TestEntity.builder()
      .id(Long.valueOf(request.id()))
      .content(request.content())
      .build();

    testService.update(testEntity);
    return ApiResponse.onSuccess();
  }

  /**
   * 리소스 삭제 테스트
   * @param id : 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @DeleteMapping(value = "/delete/{id}")
  @Operation(description = "리소스 삭제 테스트")
  public ApiResponse<Void> delete(@PathVariable("id") String id) {
    testService.delete(Long.valueOf(id));
    return ApiResponse.onSuccess();
  }
}
