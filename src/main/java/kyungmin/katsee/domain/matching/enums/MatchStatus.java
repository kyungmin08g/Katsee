package kyungmin.katsee.domain.matching.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchStatus {
  ATMOSPHERE("대기"),
  FRIEND("친구"),
  REFUSE("거절");

  public final String value;
}
