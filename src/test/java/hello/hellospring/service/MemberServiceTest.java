package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // new MemberService()를 생성할 때 Memory~Repository()를 직접 넣어줌
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given 뭔가 주어졌을 때
        Member member = new Member();
        // 여기에서 값을 spring으로 지정하면 DB에 계속 값이 누적이 되기 때문에 오류 생김
        // afterEach로 계속 초기화해주면 괜춘.
        member.setName("hello");

        // when 이걸 실행했을 때
        // 뭘 검증할거냐?
        Long saveId = memberService.join(member);

        // then 결과가 이게 나와야 함
        // 검증
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        //                                      2. 예외상황이 터짐                    1. 이 로직을 태울 때
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /*
        try {
            // 두번쨰 join으로 이름이 spring으로 똑같음
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}