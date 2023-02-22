package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();

    // 직접 new로 생성하는 것이 아니라 외부에서 넣어주도록 만들어줌 (TEST 파일에서 사용하려고)
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        // 아니야 파일이 바뀌었다구!!!!
    }

    // 회원가입
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원X

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        // null이 아니라 값이 있으면
        result.ifPresent(m -> {
            // 이 로직이 작동. Optinal로 되어있기 때문에 가능한 일
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // Optinal을 바로 반환하는 것은 권장하지 않음.
        // 어차피 result가 반환이 되었기 떄문에 바로 ifPresent 사용 가능
        // 근데 이 정도로 복잡한 건 메서드로 내보낼 것임 ㅋㅋ 짜증
        /*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        // 중복회원검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        // 회원가입하면 회원번호만 리턴해줌
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
