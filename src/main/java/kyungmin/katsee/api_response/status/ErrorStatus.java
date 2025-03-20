package kyungmin.katsee.api_response.status;

import kyungmin.katsee.api_response.exception.dto.ExceptionDto;
import kyungmin.katsee.api_response.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404"),
  KEY_NOT_EXIST(HttpStatus.NOT_FOUND, "COMMON403"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401"),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED");

  private final HttpStatus httpStatus;
  private final String code;

  @Override
  public ExceptionDto getErrorStatus() {
    return ExceptionDto.builder()
      .httpStatus(httpStatus)
      .code(code)
      .isSuccess(false)
      .build();
  }
}
