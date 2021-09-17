package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InterfaceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    public interface Abc {
        Long setTimer();
    }

    public interface Def extends Abc {
        Member findTimer();
    }

    static class Abcdef implements Def {
        @Override
        public Member findTimer() {
            Member member = new Member();
            member.setName("jeon");
            return member;
        }

        @Override
        public Long setTimer() {
            return null;
        }


    }

    @Test
    void executeTest() {
        Abcdef abcdef = new Abcdef();
        Member member = new Member();
        member.setName("jeon");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);

    }
}
