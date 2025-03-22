package kyungmin.katsee.domain.member.repository;

import kyungmin.katsee.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
