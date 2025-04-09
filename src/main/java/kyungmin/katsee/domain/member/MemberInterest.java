package kyungmin.katsee.domain.member;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.enums.Interest;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "interest")
public class MemberInterest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Comment("관심사 유형")
  private Interest interest;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @Comment("회원")
  private Member member;

}
