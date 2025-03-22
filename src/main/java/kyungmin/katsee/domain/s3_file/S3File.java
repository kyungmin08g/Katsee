package kyungmin.katsee.domain.s3_file;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "s3_file")
public class S3File {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "s3_file_id")
  private Long id;

  @Column(name = "original_name", nullable = false)
  @Comment("원본 파일 이름")
  private String originalName;

  @Column(name = "file_name", nullable = false)
  @Comment("파일 이름")
  private String fileName;

  @Column(name = "file_url")
  @Comment("S3 파일 경로")
  private String fileUrl;

}
