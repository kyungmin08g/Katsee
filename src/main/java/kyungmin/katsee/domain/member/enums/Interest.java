package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Interest {
  MUSIC("음악"),
  GAME("게임"),
  MOVIEDRAMA("영화 & 드라마"),
  READING("독서"),
  ART("예술"),
  WORKOUT("운동"),
  TRAVEL("여행"),
  FOODEXPLORATION("맛집 탐방"),
  IT("IT");

  public final String value;
}
