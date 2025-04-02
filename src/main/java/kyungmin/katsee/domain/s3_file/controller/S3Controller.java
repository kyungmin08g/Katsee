package kyungmin.katsee.domain.s3_file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyungmin.katsee.api_response.ApiResponse;
import kyungmin.katsee.domain.s3_file.controller.response.GetS3FileResponse;
import kyungmin.katsee.domain.s3_file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/s3")
@RequiredArgsConstructor
@Tag(name = "S3 관련 API")
public class S3Controller {
  private final S3Service s3Service;

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(description = "S3 이미지 업로드")
  public ApiResponse<GetS3FileResponse> uploadFile(@RequestPart(name = "file") MultipartFile file) {
    return ApiResponse.onSuccess(s3Service.upload(file));
  }

  @DeleteMapping(value = "/delete")
  @Operation(description = "S3 이미지 삭제")
  public ApiResponse<Void> deleteFile(@RequestParam String fileUrl) {
    s3Service.delete(fileUrl);
    return ApiResponse.onSuccess();
  }
}
