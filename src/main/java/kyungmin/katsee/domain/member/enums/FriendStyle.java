package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendStyle {
  DAILYTALK("일상 대화 친구"),
  STUDY("공부/업무 친구"),
  WORKOUT("운동 메이트"),
  GAME("게임 친구"),
  TRAVEL("여행 친구"),
  EMOTIONALSHARING("감정 공유 친구"),
  GROUPACTIVITY("그룹 활동 친구");

  public final String value;
}
