package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OnlineTalkStyle {
  VOICE("음성 대화 가능"),
  VIDEO("영상 통화 가능"),
  CHAT("채팅으로만 대화 가능");

  public final String value;
}
