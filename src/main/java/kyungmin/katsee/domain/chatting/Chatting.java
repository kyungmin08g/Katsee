package kyungmin.katsee.domain.chatting;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chatting")
public class Chatting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(updatable = false)
  @Comment("생성일")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "friend_id")
  @Comment("친구")
  private Member friend;

  @OneToMany(mappedBy = "chatting", orphanRemoval = true)
  @Column(name = "chat_content")
  @Comment("채팅 내역")
  private List<ChatContent> chatContents;

}
