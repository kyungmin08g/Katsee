package kyungmin.katsee.api_response.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class ExceptionDto {
  private final HttpStatus httpStatus;
  private final String code;
  private final String message;
  private final Boolean isSuccess;
}
