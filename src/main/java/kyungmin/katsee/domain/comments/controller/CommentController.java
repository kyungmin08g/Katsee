package kyungmin.katsee.domain.comments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.comments.controller.request.CreateCommentRequest;
import kyungmin.katsee.domain.comments.controller.request.UpdateCommentRequest;
import kyungmin.katsee.domain.comments.controller.response.GetCommentListResponse;
import kyungmin.katsee.domain.comments.controller.response.GetCommentResponse;
import kyungmin.katsee.domain.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
@Tag(name = "댓글 관련 API")
public class CommentController {
  private final CommentService commentService;

  @PostMapping(value = "/create")
  @Operation(description = "댓글 생성")
  public ApiResponse<?> crateComment(@RequestBody CreateCommentRequest request) {
    commentService.createComment(request);
    return ApiResponse.onSuccess();
  }

  @GetMapping(value = "/{id}")
  @Operation(description = "댓글 조회")
  public ApiResponse<GetCommentResponse> getComment(@PathVariable("id") String commentId) {
    return ApiResponse.onSuccess(commentService.getComment(commentId));
  }

  @GetMapping(value = "/list/{id}")
  @Operation(description = "공지에 대한 댓글 목록 조회")
  public ApiResponse<List<GetCommentListResponse>> getCommentList(@PathVariable("id") String noticeId) {
    return ApiResponse.onSuccess(commentService.getCommentList(noticeId));
  }

  @PatchMapping(value = "/update")
  @Operation(description = "댓글 수정")
  public ApiResponse<?> updateComment(@RequestBody UpdateCommentRequest request) {
    commentService.updateComment(request);
    return ApiResponse.onSuccess();
  }

  @DeleteMapping(value = "/delete/{noticeId}")
  @Operation(description = "댓글 삭제")
  public ApiResponse<?> deleteComment(@PathVariable("noticeId") String id) {
    commentService.deleteComment(id);
    return ApiResponse.onSuccess();
  }
}
