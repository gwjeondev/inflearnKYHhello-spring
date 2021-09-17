package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
   }

    // insert 할 때 .. Member class를 entity화 했기 때문에 객체 그 자체를 바로 insert 가능.
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // key를 조외 할 때 에는 ..
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // key가 아닌 값을(단일값이 아닌) 조회 할 때 에는 ...
    @Override
    public Optional<Member> findByName(String name) {
        // sql은 table을 대상으로 query것이나 jpql은 객체를 대상으로 query하는 것.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    // key가 아닌 값을(단일값이 아닌) 조회 할 때 에는 ...
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
