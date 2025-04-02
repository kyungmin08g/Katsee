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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class S3Service {
  private final S3FileRepository s3FileRepository;
  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  public GetS3FileResponse upload(MultipartFile file) {
    if (file.isEmpty()) throw new GeneralException(ErrorStatus.BAD_REQUEST, "파일이 비어있습니다.");

    String originalFileName = file.getOriginalFilename().replace(" ", ""); // 띄어쓰기 제거
    String fileName = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()) + "." +
      originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 이상한 이모지를 포함한 이름이 있을 수 있어서 예비로 구현

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(file.getContentType());
    objectMetadata.setContentLength(file.getSize());

    try {
      amazonS3.putObject(bucket, fileName, file.getInputStream(), objectMetadata);
      s3FileRepository.save(
        S3File.builder()
          .originalName(originalFileName)
          .fileName(fileName)
          .fileUrl(amazonS3.getUrl(bucket, fileName).toString())
          .build()
      );
    } catch (IOException e) {
      throw new GeneralException(ErrorStatus.INTERNAL_ERROR, "S3 이미지 업로드가 실패했습니다.");
    }

    return GetS3FileResponse.builder()
      .originalName(originalFileName)
      .fileName(fileName)
      .s3Url(amazonS3.getUrl(bucket, fileName).toString())
      .build();
  }

  public void delete(String fileUrl) {
    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    amazonS3.deleteObject(bucket, fileName);
    s3FileRepository.deleteByFileName(fileName);
  }
}
