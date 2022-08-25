package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 메모리이니까 저장을 어딘가에 해놔야함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
              // null이 반환될 가능성이 있으면 Optional로 한 번 감쌈
              // 한 번 감싸서 반환면 클라이언트가 뭘 할 수가 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // member.getname()이 파라미터로 넘어온 name이랑 같은지 확인
                // 같은 경우에만 필터링, 값 반환
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store.values()가 member 의 값!
    }

    public void clearStore(){
        store.clear();
    }
}
