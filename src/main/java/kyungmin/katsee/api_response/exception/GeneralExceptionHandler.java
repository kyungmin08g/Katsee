package kyungmin.katsee.api_response.exception;

import kyungmin.katsee.api_response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandler {

  public ApiResponse<?> exceptionHandler() {
    
  }

}
