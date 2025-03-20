package kyungmin.katsee.api_response.exception;

import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.api_response.exception.dto.ExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kyungmin.katsee.api_response.exception.GeneralException.getErrorHttpStatus;

@RestControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(value = GeneralException.class)
  public ApiResponse<?> exceptionHandler() {
    ExceptionDto exception = getErrorHttpStatus();
    return ApiResponse.onFailure(
      exception.getCode(),
      exception.getMessage(),
      null
    );
  }
}
