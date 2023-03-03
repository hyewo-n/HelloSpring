package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // @Controller이 노테이션 되어있으면 스프링 컨테이너에 등록을 하고 거기서 받아서 사용하게 됨
    // 그런데 new MemberService()로 생성하게 되면 MemberController 말고 다른 여러 Controller들이 가져다 사용 가능
    private final MemberService memberService; // = new MemberService();


    @Autowired    // SPRING이 MemberContorller에 있는 MemberService를 가져다가 연결을 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ~ 여기로 가겠다
    @GetMapping("/members/new")
    public String createForm() {
        // 아무것도 하는 것 없이 createMemberForm으로 이동, 그 html이 화면에 뿌려짐
        return "members/createMemberForm";
    }

    // 데이터를 form에 넣어서 전달할 때 Post 방식 사용 (Get 방식은 주로 조회할 때)
    // createMemberForm에서 값을 post로 넘기면 여기서 가져옴
    // url은 같지만 여긴 post이기 때문에 이게 호출되는 것
    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원가입이 끝났으니까 홈 화면으로 보냄
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
