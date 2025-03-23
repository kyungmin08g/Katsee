package kyungmin.katsee.domain.member.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider;
import kyungmin.katsee.domain.member.security.redis.RedisService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
  private final JwtProvider jwtProvider;
  private final RedisService redisService;

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
        authentication(accessToken);
        filterChain.doFilter(request, response);
        return;
      } else if (refreshToken != null) {
        log.info("리프레쉬 토큰 발급");
        authentication(refreshToken);
        response.addHeader("Authorization", "Bearer " + refreshToken);
        filterChain.doFilter(request, response);
        return;
      } else {
        filterChain.doFilter(request, response);
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private void authentication(String token) {
    String memberId = jwtProvider.getMemberId(token);
    String role = jwtProvider.getRole(token);
    SecurityContextHolder.getContext().setAuthentication(
      new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority(role)))
    );
  }
}
