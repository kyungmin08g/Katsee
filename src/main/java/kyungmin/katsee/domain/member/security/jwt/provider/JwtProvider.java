package kyungmin.katsee.domain.member.security.jwt.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
  private final Key secretKey;
  private final Claims claims;

  public static String accessTokenExpireTime;
  public static String refreshTokenExpireTime;

  public JwtProvider(
    @Value("${jwt.secret-key}") String secretKey,
    @Value("${jwt.access-token-expire-time}") String accessTokenExpire,
    @Value("${jwt.refresh-token-expire-time}") String refreshTokenExpire
  ) {
    this.claims = Jwts.claims();
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(
      Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8))
    ));
    accessTokenExpireTime = accessTokenExpire;
    refreshTokenExpireTime = refreshTokenExpire;
  }

  public String createToken(String userId, String role, String expireTime) {
    claims.put("memberId", userId);
    claims.put("role", role);

    return Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setHeaderParam("alg", "HS256")
      .addClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + (Long.parseLong(expireTime) * 1000)))
      .signWith(secretKey)
      .compact();
  }

  public String getMemberId(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("memberId", String.class);
    } catch (ExpiredJwtException e) {
      return e.getClaims().get("memberId", String.class);
    }
  }

  public String getRole(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("role", String.class);
    } catch (ExpiredJwtException e) {
      return e.getClaims().get("role", String.class);
    }
  }

  public boolean getValidateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException e) {
      log.error("잘못된 인증 토큰: {}", String.valueOf(e));
    } catch (ExpiredJwtException e) {
      log.error("만료된 인증 토큰: {}", String.valueOf(e));
    } catch (UnsupportedJwtException e) {
      log.error("지원되지 않는 인증 토큰: {}", String.valueOf(e));
    } catch (IllegalArgumentException e) {
      log.error("인증 토큰 주장 문자열이 비어 있습니다: {}", String.valueOf(e));
    }

    return false;
  }
}
