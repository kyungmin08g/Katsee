package kyungmin.katsee.domain.member.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider;
import kyungmin.katsee.domain.member.security.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider.accessTokenExpireTime;
import static kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider.refreshTokenExpireTime;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final RedisService redisService;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String memberId = request.getParameter("memberId");
    String password = request.getParameter("password");

    return authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(memberId, password)
    );
  }

  @Override
  protected void successfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain,
    Authentication authResult
  ) throws IOException, ServletException {
    String memberId = authResult.getName();
    String role = authResult.getAuthorities().iterator().next().getAuthority();
    String accessToken = jwtProvider.createToken(memberId, role, accessTokenExpireTime);
    String refreshToken = jwtProvider.createToken(memberId, role, refreshTokenExpireTime);

    // 액세스 토큰 & 리프레시 토큰 발급
    response.addHeader("Authorization", "Bearer " + accessToken);
    redisService.saveRefreshToken(memberId, refreshToken);
    log.info("Login success!");
  }

  @Override
  protected void unsuccessfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException failed
  ) throws IOException, ServletException {
    log.error("Login failure.. {}", failed.getMessage());
  }
}
