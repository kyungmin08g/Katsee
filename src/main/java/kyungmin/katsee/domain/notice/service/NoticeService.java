package kyungmin.katsee.domain.notice.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.notice.Notice;
import kyungmin.katsee.domain.notice.controller.request.CreateNoticeRequest;
import kyungmin.katsee.domain.notice.controller.response.GetNoticeResponse;
import kyungmin.katsee.domain.notice.repository.NoticeRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 여기선 CRUD만 하면됨.
 */

@Service
@RequiredArgsConstructor
public class NoticeService {
  private final NoticeRepository noticeRepository;
  private final MemberRepository memberRepository;

  // 공지 생성
  public void create(CreateNoticeRequest request) {
    String memberId = SecurityUtil.authMemberId();
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST, "회원을 찾을 수 없습니다."));

    if (member.getRole().equals(Role.ADMIN)) {
      noticeRepository.save(
        Notice.builder()
          .thumbnailUrl(request.thumbnail_url())
          .title(request.title())
          .content(request.content())
          .member(member)
          .build()
      );
    }
  }

  // 공지 조회
  public GetNoticeResponse getNotice(String noticeId) {
    Notice notice = noticeRepository.findById(Long.parseLong(noticeId))
      .orElseThrow(() -> new GeneralException(ErrorStatus.KEY_NOT_EXIST));
    return GetNoticeResponse.builder()
      .id(notice.getId())
      .thumbnailUrl(notice.getThumbnailUrl())
      .title(notice.getTitle())
      .content(notice.getContent())
      .createdAt(notice.getCreatedAt())
      .build();
  }

}
