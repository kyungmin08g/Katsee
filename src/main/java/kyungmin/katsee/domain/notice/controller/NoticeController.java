package kyungmin.katsee.domain.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.notice.controller.request.CreateNoticeRequest;
import kyungmin.katsee.domain.notice.controller.request.UpdateNoticeRequest;
import kyungmin.katsee.domain.notice.controller.response.GetNoticeResponse;
import kyungmin.katsee.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Tag(name = "공지사항 관련 API")
public class NoticeController {
  private final NoticeService noticeService;

  /**
   * 공지 생성 - 관리자만 접근 가능
   * @param request : 공지 생성 관련 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/create")
  @Operation(description = "공지 생성")
  public ApiResponse<Void> create(@RequestBody CreateNoticeRequest request) {
    noticeService.createNotice(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 특정 공지 조회
   * @param noticeId : 공지 아이디
   * @return : 성공시 공지 조회 응답 객체 반환
   */
  @GetMapping(value = "/{id}")
  @Operation(description = "공지 조회")
  public ApiResponse<GetNoticeResponse> getNotice(@PathVariable("id") String noticeId) {
    return ApiResponse.onSuccess(noticeService.getNotice(noticeId));
  }

  /**
   * 공지 목록 조회
   * @return : 성공시 공지 목록 반환
   */
  @GetMapping(value = "/list")
  @Operation(description = "공지 목록 조회")
  public ApiResponse<List<GetNoticeResponse>> getNoticeList() {
    return ApiResponse.onSuccess(noticeService.getNoticeList());
  }

  /**
   * 공지 수정 - 관리자만 접근 가능
   * @param request : 공지 수정 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PatchMapping(value = "/update")
  @Operation(description = "공지 수정")
  public ApiResponse<Void> update(@RequestBody UpdateNoticeRequest request) {
    noticeService.updateNotice(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 특정 공지 삭제 - 관리자만 접근 가능
   * @param noticeId : 공지 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @DeleteMapping(value = "/delete/{id}")
  @Operation(description = "공지 삭제")
  public ApiResponse<Void> delete(@PathVariable("id") String noticeId) {
    noticeService.deleteNotice(noticeId);
    return ApiResponse.onSuccess();
  }
}
