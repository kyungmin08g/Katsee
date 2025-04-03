package kyungmin.katsee.domain.chatting.repository;

import kyungmin.katsee.domain.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
  List<Chatting> findByMember_MemberId(String memberId);
}
