package kyungmin.katsee.domain.s3_file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.s3_file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/s3")
@RequiredArgsConstructor
@Tag(name = "S3 관련 API")
public class S3Controller {
  private final S3Service s3Service;

  // S3 파일 업로드 및 조회 API
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(description = "이미지 업로드")
  public ApiResponse<String> uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
    return ApiResponse.onSuccess(s3Service.upload(file));
  }
  // S3 파일 삭제 API
}
