package kyungmin.katsee.domain.member.security.user.dto;

import kyungmin.katsee.domain.member.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class UserAuthDto {
  private String memberId;
  private String password;
  private Role role;
}
