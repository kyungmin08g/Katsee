package kyungmin.katsee.domain.comments.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.comments.Comments;
import kyungmin.katsee.domain.comments.controller.request.CreateCommentRequest;
import kyungmin.katsee.domain.comments.controller.request.UpdateCommentRequest;
import kyungmin.katsee.domain.comments.controller.response.GetCommentResponse;
import kyungmin.katsee.domain.comments.repository.CommentsRepository;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.notice.Notice;
import kyungmin.katsee.domain.notice.repository.NoticeRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentsRepository commentRepository;
  private final NoticeRepository noticeRepository;
  private final MemberRepository memberRepository;

  // 댓글 생성
  public void createComment(CreateCommentRequest request) {
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
        .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
    Notice notice = noticeRepository.findById(Long.parseLong(request.noticeId()))
        .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "공지를 찾을 수 없습니다."));

    commentRepository.save(
      Comments.builder()
        .content(request.content())
        .notice(notice)
        .member(member)
        .build()
    );
  }

  // 댓글 조회
  public GetCommentResponse getComment(String commentId) {
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
    Comments comment = commentRepository.findById(Long.parseLong(commentId))
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "댓글을 찾을 수 없습니다."));

    return GetCommentResponse.builder()
      .commentId(Long.parseLong(commentId))
      .noticeId(comment.getNotice().getId())
      .memberId(member.getMemberId())
      .content(comment.getContent())
      .createAt(comment.getCreatedAt())
      .build();
  }

  // 댓글 수정
  public void updateComment(UpdateCommentRequest request) {
    Member member = memberRepository.findById(SecurityUtil.authMemberId())
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));
    Notice notice = noticeRepository.findById(Long.parseLong(request.noticeId()))
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "공지를 찾을 수 없습니다."));
    Comments comment = commentRepository.findById(Long.parseLong(request.commentId()))
        .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "댓글을 찾을 수 없습니다."));

    commentRepository.save(
      Comments.builder()
        .id(comment.getId())
        .notice(notice)
        .content(request.content())
        .member(member)
        .build()
    );
  }

  // 댓글 삭제
  public void deleteComment(String commentId) {
    commentRepository.deleteById(Long.parseLong(commentId));
  }
}
