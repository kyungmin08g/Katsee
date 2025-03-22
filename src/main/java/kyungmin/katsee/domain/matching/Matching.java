package kyungmin.katsee.domain.matching;

import jakarta.persistence.*;
import kyungmin.katsee.domain.matching.enums.MatchStatus;
import kyungmin.katsee.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "matching")
public class Matching {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "match_status_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @Comment("회원")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "friend_id")
  @Comment("친구")
  private Member friend;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  @Comment("매칭 상태")
  private MatchStatus matchStatus;

}
