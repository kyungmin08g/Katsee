package kyungmin.katsee.domain.notice.repository;

import kyungmin.katsee.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
