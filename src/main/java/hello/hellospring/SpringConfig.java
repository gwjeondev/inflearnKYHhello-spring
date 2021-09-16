package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/*controller, service, repository에 직접 annotation을 등록하지 않고
스프링 빈을 직접 등록하는 방법*/
@Configuration
public class SpringConfig {


    //    private DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

/*    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/


    /*    스프링이 @Bean anotation을 보고 컨테이너에 빈으로 등록한 다음
        MemberService가 의존성을 가지는 memberReopository을 넣어준다.*/
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

/*    스프링이 @Bean anotation을 보고 컨테이너에 빈으로 등록한다.
    MemoryRepository는 인터페이스이고 return인 MemoryMememoryRepository는 클래스 인스턴스임.*/
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
