package kyungmin.katsee.domain.notice.service;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.notice.Notice;
import kyungmin.katsee.domain.notice.controller.request.CreateNoticeRequest;
import kyungmin.katsee.domain.notice.repository.NoticeRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
          .build()
      );
    }
    System.out.println("??");
  }

}
