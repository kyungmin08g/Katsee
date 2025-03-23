package kyungmin.katsee.domain.member.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider;
import kyungmin.katsee.domain.member.security.redis.RedisService;
import kyungmin.katsee.domain.member.security.user.UserAuthDetails;
import kyungmin.katsee.domain.member.security.user.service.UserAuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final RedisService redisService;
  private final MemberRepository memberRepository;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String accessToken = authHeader.split(" ")[1];
      String refreshToken = redisService.getRefreshToken(jwtProvider.getMemberId(accessToken));

      if (jwtProvider.getValidateToken(accessToken)) {
        log.info("액세스 토큰이 유효함");
        authUser(accessToken);
        filterChain.doFilter(request, response);
        return;
      } else if (refreshToken != null) {
        log.info("리프레쉬 토큰 발급");
        authUser(refreshToken);
        response.addHeader("Authorization", "Bearer " + refreshToken);
        filterChain.doFilter(request, response);
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private void authUser(String token) {
    String memberId = jwtProvider.getMemberId(token);
    String role = jwtProvider.getRole(token);
    Authentication auth = new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority(role)));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }
}
