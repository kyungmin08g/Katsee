package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RelationshipDepth {
  LIGHTLY("가볍게 연락하는 정도"),
  OFTEN("자주 소통하는 사이"),
  OFFLINE("오프라인에서 만나는 것도 가능");

  public final String value;
}
