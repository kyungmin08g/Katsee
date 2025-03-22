package kyungmin.katsee.domain.notice;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice")
public class Notice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "thumbnail_url")
  @Comment("썸네일 사진")
  private String thumbnailUrl;

  @Column(nullable = false)
  @Comment("제목")
  private String title;

  @Column(nullable = false)
  @Comment("내용")
  private String content;

  @CreatedDate
  @Column(updatable = false)
  @Comment("생성된 날짜/시간")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Comment("수정된 날짜/시간")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  @Comment("회원")
  private Member member;

}
