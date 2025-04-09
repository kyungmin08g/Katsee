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

  /**
   *  댓글 생성
   * @param request : 댓글 생성 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PostMapping(value = "/create")
  @Operation(description = "댓글 생성")
  public ApiResponse<Void> crateComment(@RequestBody CreateCommentRequest request) {
    commentService.createComment(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 특정 댓글 조회 - 특정 댓글 아이디를 사용하여 댓글 조회
   * @param commentId : 댓글 아이디
   * @return : 성공시 댓글 조회 응답 객체 반환
   */
  @GetMapping(value = "/{id}")
  @Operation(description = "댓글 조회")
  public ApiResponse<GetCommentResponse> getComment(@PathVariable("id") String commentId) {
    return ApiResponse.onSuccess(commentService.getComment(commentId));
  }

  /**
   * 댓글 목록 조회 - 특정 공지에 대한 댓글 목록 조회
   * @param noticeId : 공지 아이디
   * @return : 성공시 댓글 목록 응답 객체 반환
   */
  @GetMapping(value = "/list/{id}")
  @Operation(description = "공지에 대한 댓글 목록 조회")
  public ApiResponse<List<GetCommentListResponse>> getCommentList(@PathVariable("id") String noticeId) {
    return ApiResponse.onSuccess(commentService.getCommentList(noticeId));
  }

  /**
   * 댓글 수정 - 댓글 아이디를 사용하여 댓글 수정
   * @param request : 댓글 수정 요청 객체
   * @return : 성공시 성공 JSON 반환
   */
  @PatchMapping(value = "/update")
  @Operation(description = "댓글 수정")
  public ApiResponse<Void> updateComment(@RequestBody UpdateCommentRequest request) {
    commentService.updateComment(request);
    return ApiResponse.onSuccess();
  }

  /**
   * 특정 댓글 삭제 - 댓글 아이디를 사용하여 댓글 삭제
   * @param id : 댓글 아이디
   * @return : 성공시 성공 JSON 반환
   */
  @DeleteMapping(value = "/delete/{commentId}")
  @Operation(description = "댓글 삭제")
  public ApiResponse<Void> deleteComment(@PathVariable("commentId") String id) {
    commentService.deleteComment(id);
    return ApiResponse.onSuccess();
  }
}
