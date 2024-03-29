package hello.hellospring.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    // 요구사항

    // DB가 알아서 생성해주는 것 = IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // 데이터를 구분하기 위해서 시스템이 값을 정함
    private String name;   // 회원가입 시 고객이 입력하는 값

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
