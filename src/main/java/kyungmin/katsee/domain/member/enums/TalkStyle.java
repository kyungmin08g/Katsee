package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TalkStyle {
  LONGTALK("길고 깊은 대화를 좋아함"),
  SHORTTALK("짧고 가벼운 대화를 선호함"),
  MOOD("분위기에 따라 다름");

  public final String value;
}
