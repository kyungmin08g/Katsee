package kyungmin.katsee.domain.chatting.repository;

import kyungmin.katsee.domain.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {

}
