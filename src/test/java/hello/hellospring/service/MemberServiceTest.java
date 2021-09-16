package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memoryRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memoryRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryRepository);
    }

    @AfterEach
    void afterEach() {
        memoryRepository.clearStore();
    }

    @Test
    void join() {
        // given ~ 주어졌을 때
        Member member = new Member();
        member.setName("JGW");

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

    @Test
    void test1() {
        List<String> list = Arrays.asList("A","B","C","D");

        Optional<String> result = list.stream().findAny();

        //assertTrue(result.isPresent());
        //assertThat(result.get(), anyOf(is("A"), is("B"), is("C"), is("D")));
    }
}