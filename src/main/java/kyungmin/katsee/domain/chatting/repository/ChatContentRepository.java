package kyungmin.katsee.domain.chatting.repository;

import kyungmin.katsee.domain.chatting.ChatContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatContentRepository extends JpaRepository<ChatContent, Long> {

}
