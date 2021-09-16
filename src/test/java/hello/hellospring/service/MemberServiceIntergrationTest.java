package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {
    private final MemberService memberService;
    private final MemberRepository memoryRepository;

/*
    @Autowired MemberRepository memoryRepository;
    @Autowired MemberService memberService;
*/

    @Autowired
    public MemberServiceIntergrationTest(MemberRepository memoryRepository, MemberService memberService) {
        this.memoryRepository = memoryRepository;
        this.memberService = memberService;
    }

    @Test
    //@Commit 커밋 annotation
    void join() {
        // given ~ 주어졌을 때
        Member member = new Member();
        member.setName("abcde");

        // when ~ 했을 때
        Long saveId = memberService.join(member);

        // then ~ 그렇 다면 ~ 그렇게 하면
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateJoin() {
        // given
        Member member1 = new Member();
        member1.setName("JGW");

        Member member2 = new Member();
        member2.setName("JGW");

        // when
        memberService.join(member1);

        // try & catch를 이용한 검증부
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        }*/

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}