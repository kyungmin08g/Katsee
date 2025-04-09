package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
  MAN("남자"),
  FEMALE("여자");

  public final String value;
}
