package kyungmin.katsee.domain.s3_file.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Schema(description = "S3 파일 정보 응답 객체")
public class GetS3FileResponse {

  @Schema(description = "원본 이름")
  private String originalName;

  @Schema(description = "파일 이름")
  private String fileName;

  @Schema(description = "S3 파일 URL")
  private String s3Url;
}
