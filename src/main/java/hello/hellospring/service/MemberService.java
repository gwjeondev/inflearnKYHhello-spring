package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/* @Service anotation이 있을 경우 Service 판단하고
스프링 컨테이너에 스프링 빈으로 등록한다. @Component를 포함 */
//@Service
public class MemberService {
    private MemberRepository memberRepository;
    //@Autowired private MemberRepository memberRepository; 필드 의존성 주입 방식
    
    /*@Autowired setter 주입 방식
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*    의존성 주입(DI, Denpendency Injection):
          MemberService Bin은 MemberRepository Bin에
          의존성을 띄므로 스프링이 시작 될 때 @Autowired anotation이
          있을 경우 컨테이너에 있는 MemberRepository를 대신 주입해준다.*/
    // 생성자 의존성 주입 방식
  //  @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        vaildateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void vaildateDuplicateMember(Member member) {
        /* findByName Method로 부터 return 받은 optional 객체를 ifPresent를 통해 값이 있으면(null이 아니면)
        이미 존재하는 회원임으로 예외를 던진다.*/
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * Id 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

