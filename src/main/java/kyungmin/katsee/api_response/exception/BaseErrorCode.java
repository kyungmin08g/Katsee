package kyungmin.katsee.api_response.exception;

import kyungmin.katsee.api_response.exception.dto.ExceptionDto;

public interface BaseErrorCode {
  ExceptionDto getErrorStatus();
}
