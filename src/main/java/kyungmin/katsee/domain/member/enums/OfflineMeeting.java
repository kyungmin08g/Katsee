package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OfflineMeeting {
  ONLINE("온라인 친구만 찾고 싶어요"),
  OFFLINE("근처에서 만날 친구도 괜찮아요");

  public final String value;
}
