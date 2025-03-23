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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
        System.out.println("???");
      }

      // 액세스 토큰의 유효시간이 지났는데 리프레쉬 토큰이 유효하다면 OK
      if (refreshToken != null) {

      }
    }

    filterChain.doFilter(request, response);
  }
}
