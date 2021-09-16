package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*스프링 컨테이너가 @Controller anotation이 있을 경우 Controller로 판단하고
스프링 빈으로 등록한다 @Component를 포함.*/
@Controller
public class MemberController {

    private final MemberService memberService;
    // @Autowired private MemberService memberService; 필드 의존성 주입 방식

/*    @Autowired setter 의존성 주입 방식
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/

    /*    의존성 주입(DI, Denpendency Injection):
          MemberController Bin은 memberService Bin에
          의존성을 띄므로 스프링이 시작 될 때 @Autowired anotation이
          있을 경우 컨테이너에 있는 MemberService를 대신 주입해준다.*/
    // 생성자 의존성 주입 방식
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createJoin(MemberForm form) {
        System.out.println(form.getName());
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/list";
    }

}