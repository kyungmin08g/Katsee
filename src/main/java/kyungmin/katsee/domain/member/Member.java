package kyungmin.katsee.domain.member;

import jakarta.persistence.*;
import kyungmin.katsee.domain.member.enums.Gender;
import kyungmin.katsee.domain.member.enums.Role;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {

  @Id
  @Column(nullable = false, name = "member_id")
  @Comment("회원 ID")
  private String memberId;

  @Comment("프로필 사진")
  private String profileUrl;

  @Column(nullable = false, name = "nick_name")
  @Comment("닉네임")
  private String nickName;

  @Column(nullable = false)
  @Comment("비밀번호")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Comment("권한")
  private Role role;

  @Comment("소개")
  private String introduction;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Comment("성별")
  private Gender gender;

  @Column(length = 10)
  @Comment("나이")
  private String age;

  @CreatedDate
  @Column(updatable = false)
  @Comment("생성된 날짜/시간")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Comment("수정된 날짜/시간")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("관심사 유형")
  private List<MemberInterest> interest;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("성격 유형")
  private List<MemberPersonalityType> personalityType;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("대화 스타일")
  private List<MemberTalkStyle> talkStyle;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("원하는 친구 스타일")
  private List<MemberFriendStyle> friendStyle;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("원하는 친구 스타일")
  private List<MemberRelationshipDepth> relationshipDepth;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("온라인 대화 가능 여부")
  private List<MemberOnlineTalkStyle> onlineTalkStyle;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("오프라인 만남 허용 여부")
  private List<MemberOfflineMeeting> isOfflineMeeting;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("관심사 선호도")
  private List<MemberInterestPreference> interestPreference;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  @Comment("관심사 레벨")
  private List<MemberInterestLevel> interestLevel;

}
