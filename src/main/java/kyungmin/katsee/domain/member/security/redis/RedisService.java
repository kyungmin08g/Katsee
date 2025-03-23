package kyungmin.katsee.domain.member.security.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static kyungmin.katsee.domain.member.security.jwt.provider.JwtProvider.refreshTokenExpireTime;

@Service
public class RedisService {
  private final RedisTemplate<String, Object> redisTemplate;

  public RedisService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void saveRefreshToken(String memberId, String refreshToken) {
    redisTemplate.opsForValue().set("RT:" + memberId, refreshToken, Duration.ofSeconds(Long.parseLong(refreshTokenExpireTime)));
  }

  public String getRefreshToken(String memberId) {
    return (String) redisTemplate.opsForValue().get("RT:" + memberId);
  }

  public void deleteRefreshToken(String memberId) {
    redisTemplate.delete("RT:" + memberId);
  }
}
