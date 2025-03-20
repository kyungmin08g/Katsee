package kyungmin.katsee.api_response.exception;

import kyungmin.katsee.api_response.exception.dto.ExceptionDto;
import kyungmin.katsee.api_response.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
  private static BaseErrorCode code;
  private static String errorMessage;

  private BaseErrorCode errorCode;

  public GeneralException(ErrorStatus status, String message) {
    super(message);
    errorMessage = message;
    setErrorCode(status);
  }

  public static ExceptionDto getErrorHttpStatus() {
    return ExceptionDto.builder()
      .httpStatus(code.getErrorStatus().getHttpStatus())
      .code(code.getErrorStatus().getCode())
      .message(errorMessage)
      .isSuccess(code.getErrorStatus().getIsSuccess())
      .build();
  }

  public void setErrorCode(BaseErrorCode errorCode) {
    this.errorCode = errorCode;
    code = this.errorCode;
  }
}
