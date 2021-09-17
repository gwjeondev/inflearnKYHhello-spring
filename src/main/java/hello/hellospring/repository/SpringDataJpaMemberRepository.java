package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*spring data jpa가 JpaRepository를 상속받는 interface가 있을 경우 그 interface를 구현하는 구현체를 만들어 스프링 빈에 등록한다.
구현 클래스를 만들 필요 없이 인터페이스만으로만 처리가 가능한 기술이 spring data jpa이다.*/
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

/*    JPQL select m from Member m where m.name = ?
    해당 예제에서는 insert와 id select, name select, all select 기능을 구현 하는데 name select를 제외한 모든 query는
    공통적으로 스프링 데이터 jpa에서 지원하며 공통적인 부분(pk)를 검색한다거나 전체 검색을 한다던지와 다른
    개별적인 query가 필요 할 때 에는 method 선언 규칙을 통해 상황에 따른 jpql을 다르게 만들어 낼 수 있다.
    findByNameAndId(String name, Long Id) 등등..... or and ... */

//    interface 상속 : JpaRepository -> PagingAndSortingRepository -> CrudRepository -> Repository
    @Override
    Optional<Member> findByName(String name);


}
