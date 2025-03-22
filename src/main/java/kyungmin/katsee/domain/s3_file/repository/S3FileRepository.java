package kyungmin.katsee.domain.s3_file.repository;

import kyungmin.katsee.domain.s3_file.S3File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface S3FileRepository extends JpaRepository<S3File, Long> {

}
