package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드가 실행이 끝날 때마다 동작. 콜백 메서드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        //이름 세팅
        member.setName("spring");

        // member를 저장
        repository.save(member);

        // DB에서 꺼냄
        Member result = repository.findById(member.getId()).get();

        // 검증
        // new에서 한 거랑 DB에서 꺼낸 거랑 똑같아야 함
        // 'result = true' 이렇게 뜸
        // 근데 결과값을 계속 글자로 볼 순 없다!
        //System.out.println("result = " + (result == member));

        // 방법1. 그래서 Assertions 사용 -> 성공하면 초록불! 결과값 따로 출력 x
        //Assertions.assertEquals(member, result);

        // 방법2. 요즘에 많이 사용.,.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }



}
