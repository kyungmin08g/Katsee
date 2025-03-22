package kyungmin.katsee.domain.comments.repository;

import kyungmin.katsee.domain.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
