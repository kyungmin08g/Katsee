package kyungmin.katsee.domain.comments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.comments.controller.request.CreateCommentRequest;
import kyungmin.katsee.domain.comments.controller.request.UpdateCommentRequest;
import kyungmin.katsee.domain.comments.controller.response.GetCommentResponse;
import kyungmin.katsee.domain.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
@Tag(name = "댓글 관련 API")
public class CommentController {
  private final CommentService commentService;

  // 댓글 생성
  @PostMapping(value = "/create")
  @Operation(description = "댓글 생성")
  public ApiResponse<?> crateComment(@RequestBody CreateCommentRequest request) {
    commentService.createComment(request);
    return ApiResponse.onSuccess();
  }

  // 댓글 조회
  @GetMapping(value = "/{id}")
  @Operation(description = "댓글 조회")
  public ApiResponse<GetCommentResponse> getComment(@PathVariable("id") String commentId) {
    return ApiResponse.onSuccess(commentService.getComment(commentId));
  }

  // 댓글 수정
  @PatchMapping(value = "/update")
  @Operation(description = "댓글 수정")
  public ApiResponse<?> updateComment(@RequestBody UpdateCommentRequest request) {
    commentService.updateComment(request);
    return ApiResponse.onSuccess();
  }

  // 댓글 삭제
}
