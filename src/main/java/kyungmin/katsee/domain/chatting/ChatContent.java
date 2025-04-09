package kyungmin.katsee.domain.chatting;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chat_content")
public class ChatContent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chat_id")
  private Long id;

  @Column(nullable = false)
  @Comment("내용")
  private String content;

  @CreatedDate
  @Column(updatable = false)
  @Comment("생성일")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @Comment("회원")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id")
  @Comment("채팅방")
  private Chatting room;

}
