package kyungmin.katsee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.formLogin(AbstractHttpConfigurer::disable);
    http.httpBasic(AbstractHttpConfigurer::disable);

    http.authorizeHttpRequests(auth -> auth
      .requestMatchers("/**", "/member/register", "/member/register", "/member/get/**", "/member/duplicate/**", "/member/delete/**")
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

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
