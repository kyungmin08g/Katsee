package kyungmin.katsee.config;

import kyungmin.katsee.domain.member.security.filter.JwtAuthFilter;
import kyungmin.katsee.domain.member.security.filter.LoginAuthFilter;
import kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider;
import kyungmin.katsee.domain.member.security.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final AuthenticationConfiguration authConfig;
  private final JwtProvider jwtProvider;
  private final RedisService redisService;
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.formLogin(AbstractHttpConfigurer::disable);
    http.httpBasic(AbstractHttpConfigurer::disable);

    http.authorizeHttpRequests(auth -> auth
      .requestMatchers(
        "/member/create", "/member/duplicate/**", "/login",
        "/s3/upload", "/s3/delete", "/wss/**", "/send/**",
        "/sub/**", "/admin/**", "/user/**", "/images/**"
      )
      .permitAll()
      .requestMatchers( // 화면 관련 엔드포인트 처리
        "/join-1", "join-2", "join-3"
      )
      .permitAll()
      .requestMatchers( // 테스트 관련 엔드포인트 처리
        "/create/**", "/detail/**",
        "/detail/list/**", "/update/**",
        "/delete/**"
      )
      .permitAll()
      .requestMatchers( // Enum API 관련 엔드포인트 처리
        "/enum/matching-status", "/enum/friend-style", "/enum/interest",
        "/enum/interest-level", "/enum/interest-preference", "/enum/offline-meeting",
        "/enum/online-talk-style", "/enum/personality-type", "/enum/relationship-depth",
        "/enum/talk-style"
      )
      .permitAll()
      .requestMatchers( // Swagger 관련 Url 처리
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/webjars/**"
      ).permitAll().anyRequest()
      .authenticated()
    );

    http.sessionManagement(session ->
      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );
    http.addFilterBefore(new LoginAuthFilter(authenticationManager(authConfig), jwtProvider, redisService), UsernamePasswordAuthenticationFilter.class);
    http.addFilterAt(jwtAuthFilter, LoginAuthFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
