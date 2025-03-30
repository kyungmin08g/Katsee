package kyungmin.katsee.domain.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.notice.controller.request.CreateNoticeRequest;
import kyungmin.katsee.domain.notice.controller.response.GetNoticeResponse;
import kyungmin.katsee.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Tag(name = "공지사항 관련 API")
public class NoticeController {
  private final NoticeService noticeService;

  // 공지 생성 (도메인: 관리자)
  @PostMapping(value = "/create")
  @Operation(description = "공지 생성")
  public ApiResponse<?> create(@RequestBody CreateNoticeRequest request) {
    noticeService.create(request);
    return ApiResponse.onSuccess();
  }

  // 공지 조회 (도메인: 공통)
  @GetMapping(value = "/{id}")
  @Operation(description = "공지 조회")
  public ApiResponse<GetNoticeResponse> create(@PathVariable("id") String noticeId) {
    return ApiResponse.onSuccess(noticeService.getNotice(noticeId));
  }

  // 공지 수정 (도메인: 관리자)
  // 공지 삭제 (도메인: 관리자)
}
