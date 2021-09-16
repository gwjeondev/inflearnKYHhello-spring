package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 저장소에 저장
    Optional<Member> findById(Long id); // 저장소에서 Id 찾음
    Optional<Member> findByName(String name); // 저장소에서 Name 찾음
    List<Member> findAll(); // 저장된 모든 Member 찾음
}

