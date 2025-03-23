package kyungmin.katsee.utils;

import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
  private static Authentication getAuthentication() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getName() == null) {
      throw new GeneralException(ErrorStatus.TOKEN_EXPIRED, "유효하지 않은 토큰 입니다.");
    }

    return authentication;
  }

  public static String authMemberId() {
    return getAuthentication().getName();
  }
}
