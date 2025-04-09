package kyungmin.katsee.domain.member;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.enums.OfflineMeeting;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "offline_meeting")
public class MemberOfflineMeeting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "is_offline")
  @Comment("오프라인 만남 허용 여부")
  private OfflineMeeting isOffline;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @Comment("회원")
  private Member member;

}
