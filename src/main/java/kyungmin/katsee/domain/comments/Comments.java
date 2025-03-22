package kyungmin.katsee.domain.comments;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.notice.Notice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comments")
public class Comments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comments_id")
  private Long id;

  @Column(nullable = false)
  @Comment("내용")
  private String content;

  @CreatedDate
  @Column(updatable = false)
  @Comment("생성일")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Comment("수정일")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parents_comments_id")
  @Comment("부모 댓글")
  private Comments parentsCommentsId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "notice_id")
  @Comment("공지사항")
  private Notice notice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @Comment("회원")
  private Member member;

}
