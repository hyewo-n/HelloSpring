package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA가 JpaRepository를 받고 있는 인터페이스를 발견하면
// 자동으로 구현체를 만들어서 스프링 빈에 등록해줌

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL : select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
