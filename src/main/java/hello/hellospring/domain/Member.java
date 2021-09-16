package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    //GeneratedValue 만들어내는 값
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략은 발생형태.identity
    private Long id;

    //@Column(name = "username") 컬럼명이 다를 경우
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
