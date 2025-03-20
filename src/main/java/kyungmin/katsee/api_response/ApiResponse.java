package kyungmin.katsee.api_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kyungmin.katsee.api_response.status.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

  private String code;
  private String message;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T result;
  private Boolean isSuccess;

  public static <T> ApiResponse<T> onSuccess(T result) {
    return new ApiResponse<T>(SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), result, true);
  }

  public static <T> ApiResponse<T> onSuccess() {
    return new ApiResponse<T>(SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), null, true);
  }

  public static <T> ApiResponse<T> onFailure(
    String code,
    String message,
    T result
  ) {
    return new ApiResponse<T>(code, message, result, false);
  }
}
