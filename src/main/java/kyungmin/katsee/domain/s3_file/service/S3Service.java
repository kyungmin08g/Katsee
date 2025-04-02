package kyungmin.katsee.domain.s3_file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.s3_file.S3File;
import kyungmin.katsee.domain.s3_file.controller.response.GetS3FileResponse;
import kyungmin.katsee.domain.s3_file.repository.S3FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
  private final S3FileRepository s3FileRepository;
  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  // S3 파일 업로드 및 조회
  public GetS3FileResponse upload(MultipartFile file) {
    String originalFileName = file.getOriginalFilename();
    assert originalFileName != null;
    String fileName = UUID.randomUUID() + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(file.getContentType());
    objectMetadata.setContentLength(file.getSize());

    try {
      amazonS3.putObject(bucket, originalFileName, file.getInputStream(), objectMetadata);
      s3FileRepository.save(
        S3File.builder()
          .originalName(file.getOriginalFilename())
          .fileName(fileName)
          .fileUrl(amazonS3.getUrl(bucket, originalFileName).toString())
          .build()
      );
    } catch (IOException e) {
      throw new GeneralException(ErrorStatus.INTERNAL_ERROR, "S3 이미지 업로드가 실패했습니다.");
    }

    return GetS3FileResponse.builder()
      .originalName(originalFileName)
      .fileName(fileName)
      .s3Url(amazonS3.getUrl(bucket, originalFileName).toString())
      .build();
  }
}
