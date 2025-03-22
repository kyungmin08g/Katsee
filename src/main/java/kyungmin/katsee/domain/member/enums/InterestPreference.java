package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterestPreference {
  POWERFUL("강한 관심"),
  GENERALLY("보통 관심"),
  LOW("약한 관심");

  public final String value;
}
