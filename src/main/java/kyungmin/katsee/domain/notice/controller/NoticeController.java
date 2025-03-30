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

  @PostMapping(value = "/create")
  @Operation(description = "공지 생성")
  public ApiResponse<?> create(@RequestBody CreateNoticeRequest request) {
    noticeService.createNotice(request);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/{id}")
  @Operation(description = "공지 조회")
  public ApiResponse<GetNoticeResponse> getNotice(@PathVariable("id") String noticeId) {
    return ApiResponse.onSuccess(noticeService.getNotice(noticeId));
  }

  @GetMapping(value = "/list")
  @Operation(description = "공지 목록 조회")
  public ApiResponse<List<GetNoticeResponse>> getAdminNoticeList() {
    return ApiResponse.onSuccess(noticeService.getAdminNoticeList());
  }

  @GetMapping(value = "/all-list")
  @Operation(description = "모든 공지 목록 조회")
  public ApiResponse<List<GetNoticeResponse>> getAllNoticeList() {
    return ApiResponse.onSuccess(noticeService.getAllNoticeList());
  }

  @PatchMapping(value = "/update")
  @Operation(description = "공지 수정")
  public ApiResponse<?> update(@RequestBody UpdateNoticeRequest request) {
    noticeService.updateNotice(request);
    return ApiResponse.onSuccess();
  }

  @DeleteMapping(value = "/delete/{id}")
  @Operation(description = "공지 삭제")
  public ApiResponse<?> delete(@PathVariable("id") String noticeId) {
    noticeService.deleteNotice(noticeId);
    return ApiResponse.onSuccess();
  }
}
