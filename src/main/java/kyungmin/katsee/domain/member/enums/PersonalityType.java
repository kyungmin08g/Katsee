package kyungmin.katsee.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonalityType {
  OUTGOING("🙏 외향적"),
  INTROVERT("😊 내향적"),
  EMOTIONAL("📚 감성적인 편"),
  LOGICAL("😎 논리적인 편"),
  HUMOROUS("😌 유머러스한 편");

  public final String value;
}
