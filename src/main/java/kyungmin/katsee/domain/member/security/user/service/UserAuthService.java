package kyungmin.katsee.domain.member.security.user.service;

import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.member.security.user.UserAuthDetails;
import kyungmin.katsee.domain.member.security.user.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
    return new UserAuthDetails(
      UserAuthDto.builder()
        .memberId(member.getMemberId())
        .password(member.getPassword())
        .role(member.getRole())
        .build()
    );
  }
}
