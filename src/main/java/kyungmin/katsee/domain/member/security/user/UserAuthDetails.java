package kyungmin.katsee.domain.member.security.user;

import kyungmin.katsee.domain.member.security.user.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserAuthDetails implements UserDetails {
  private final UserAuthDto user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return user.getRole().name();
      }
    });

    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getMemberId();
  }
}
