package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterestLevel {
  BEGINNER("초보"),
  INTERMEDIATE("중급"),
  MASTER("고수");

  public final String value;
}
