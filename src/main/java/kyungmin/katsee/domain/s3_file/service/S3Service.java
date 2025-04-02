package kyungmin.katsee.domain.s3_file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import kyungmin.katsee.domain.s3_file.repository.S3FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
  private final S3FileRepository s3FileRepository;
  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  // S3 파일 업로드 및 조회
  public String upload(MultipartFile file) throws IOException {
    String originalFileName = file.getOriginalFilename();

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(file.getContentType());
    objectMetadata.setContentLength(file.getSize());

    amazonS3.putObject(bucket, originalFileName, file.getInputStream(), objectMetadata);
    return amazonS3.getUrl(bucket, originalFileName).toString();
  }
}
